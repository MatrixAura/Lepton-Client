#include <windows.h>
#include "jni.h"
#define TEXT_SEG __declspec(allocate(".text"))
#pragma code_seg(".text")

FARPROC getProcAddress(HMODULE hModuleBase);
extern "C" PVOID64 getKernel32();

TEXT_SEG char xy_LoadLibraryA[] = "LoadLibraryA";
TEXT_SEG char xy_WriteProcessMemory[] = "WriteProcessMemory";
TEXT_SEG char xy_VirtualProtectEx[] = "VirtualProtectEx";
TEXT_SEG char xy_jvm[] = "jvm.dll";
TEXT_SEG char xy_JVM_EnqueueOperation[] = "JVM_EnqueueOperation";

int EntryMain() {
    HMODULE kernel32 = (HMODULE)getKernel32();

    // get function address ：GetProcAddress
    typedef FARPROC(WINAPI* FN_GetProcAddress)(
        _In_ HMODULE hModule,
        _In_ LPCSTR lpProcName
        );
    FN_GetProcAddress fn_GetProcAddress = (FN_GetProcAddress)getProcAddress(kernel32);

    // get function address ：LoadLibraryA
    typedef HMODULE(WINAPI* FN_LoadLibraryA)(
        _In_ LPCSTR lpLibFileName
        );
    FN_LoadLibraryA fn_LoadLibraryA = (FN_LoadLibraryA)fn_GetProcAddress(kernel32, xy_LoadLibraryA);

    // get function address ：WriteProcessMemory
    typedef BOOL(WINAPI* FN_WriteProcessMemory)(
        _In_      HANDLE hProcess,
        _In_      LPVOID lpBaseAddress,
        _In_bytecount_(nSize) LPCVOID lpBuffer,
        _In_      SIZE_T nSize,
        _Out_opt_ SIZE_T* lpNumberOfBytesWritten
        );
    FN_WriteProcessMemory fn_WriteProcessMemory = (FN_WriteProcessMemory)fn_GetProcAddress(kernel32, xy_WriteProcessMemory);

    // get function address ：VirtualProtectEx
    typedef BOOL(WINAPI* FN_VirtualProtectEx)(
        _In_  HANDLE hProcess,
        _In_  LPVOID lpAddress,
        _In_  SIZE_T dwSize,
        _In_  DWORD  flNewProtect,
        _Out_ PDWORD lpflOldProtect
    );
    FN_VirtualProtectEx fn_VirtualProtectEx = (FN_VirtualProtectEx)fn_GetProcAddress(kernel32, xy_VirtualProtectEx);

    char retn_x64[] = { '\xc3',0 };
    LPVOID dst = fn_GetProcAddress(fn_LoadLibraryA(xy_jvm), xy_JVM_EnqueueOperation);
    DWORD old;
    if (fn_VirtualProtectEx((HANDLE)-1, dst, 1, PAGE_EXECUTE_READWRITE, &old)) {
        fn_WriteProcessMemory((HANDLE)-1, dst, retn_x64, 1, NULL);
        fn_VirtualProtectEx((HANDLE)-1, dst, 1, old, &old);
    }

    return 0;
}

// get function address ：GetProcAddress
FARPROC getProcAddress(HMODULE hModuleBase)
{
    PIMAGE_DOS_HEADER lpDosHeader = (PIMAGE_DOS_HEADER)hModuleBase;
    PIMAGE_NT_HEADERS64 lpNtHeader = (PIMAGE_NT_HEADERS64)((ULONG64)hModuleBase + lpDosHeader->e_lfanew);
    if (!lpNtHeader->OptionalHeader.DataDirectory[IMAGE_DIRECTORY_ENTRY_EXPORT].Size) {
        return NULL;
    }
    if (!lpNtHeader->OptionalHeader.DataDirectory[IMAGE_DIRECTORY_ENTRY_EXPORT].VirtualAddress) {
        return NULL;
    }
    PIMAGE_EXPORT_DIRECTORY lpExports = (PIMAGE_EXPORT_DIRECTORY)((ULONG64)hModuleBase + (ULONG64)lpNtHeader->OptionalHeader.DataDirectory[IMAGE_DIRECTORY_ENTRY_EXPORT].VirtualAddress);
    PDWORD lpdwFunName = (PDWORD)((ULONG64)hModuleBase + (ULONG64)lpExports->AddressOfNames);
    PWORD lpword = (PWORD)((ULONG64)hModuleBase + (ULONG64)lpExports->AddressOfNameOrdinals);
    PDWORD  lpdwFunAddr = (PDWORD)((ULONG64)hModuleBase + (ULONG64)lpExports->AddressOfFunctions);

    DWORD dwLoop = 0;
    FARPROC pRet = NULL;
    for (; dwLoop <= lpExports->NumberOfNames - 1; dwLoop++) {
        char* pFunName = (char*)(lpdwFunName[dwLoop] + (ULONG64)hModuleBase);

        if (pFunName[0] == 'G' &&
            pFunName[1] == 'e' &&
            pFunName[2] == 't' &&
            pFunName[3] == 'P' &&
            pFunName[4] == 'r' &&
            pFunName[5] == 'o' &&
            pFunName[6] == 'c' &&
            pFunName[7] == 'A' &&
            pFunName[8] == 'd' &&
            pFunName[9] == 'd' &&
            pFunName[10] == 'r' &&
            pFunName[11] == 'e' &&
            pFunName[12] == 's' &&
            pFunName[13] == 's')
        {
            pRet = (FARPROC)(lpdwFunAddr[lpword[dwLoop]] + (ULONG64)hModuleBase);
            break;
        }
    }
    return pRet;
}
