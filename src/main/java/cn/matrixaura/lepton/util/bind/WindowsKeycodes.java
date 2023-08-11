/*
 * Copyright (c) 2002-2008 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package cn.matrixaura.lepton.util.bind;

/**
 * @author elias_naur
 */
@SuppressWarnings("unused")
final class WindowsKeycodes {
    public static final int VK_NONE = 0x00;
    public static final int VK_LBUTTON = 0x01;
    public static final int VK_RBUTTON = 0x02;
    public static final int VK_CANCEL = 0x03;
    public static final int VK_MBUTTON = 0x04;    /* NOT contiguous with L & RBUTTON */

    public static final int VK_XBUTTON1 = 0x05;    /* NOT contiguous with L & RBUTTON */
    public static final int VK_XBUTTON2 = 0x06;    /* NOT contiguous with L & RBUTTON */

    /*
     * 0x07 : unassigned
     */

    public static final int VK_BACK = 0x08;
    public static final int VK_TAB = 0x09;

    /*
     * 0x0A - 0x0B : reserved
     */

    public static final int VK_CLEAR = 0x0C;
    public static final int VK_RETURN = 0x0D;

    public static final int VK_SHIFT = 0x10;
    public static final int VK_CONTROL = 0x11;
    public static final int VK_MENU = 0x12;
    public static final int VK_PAUSE = 0x13;
    public static final int VK_CAPITAL = 0x14;

    public static final int VK_KANA = 0x15;
    public static final int VK_HANGEUL = 0x15;  /* old name - should be here for compatibility */
    public static final int VK_HANGUL = 0x15;
    public static final int VK_JUNJA = 0x17;
    public static final int VK_FINAL = 0x18;
    public static final int VK_HANJA = 0x19;
    public static final int VK_KANJI = 0x19;

    public static final int VK_ESCAPE = 0x1B;

    public static final int VK_CONVERT = 0x1C;
    public static final int VK_NONCONVERT = 0x1D;
    public static final int VK_ACCEPT = 0x1E;
    public static final int VK_MODECHANGE = 0x1F;

    public static final int VK_SPACE = 0x20;
    public static final int VK_PRIOR = 0x21;
    public static final int VK_NEXT = 0x22;
    public static final int VK_END = 0x23;
    public static final int VK_HOME = 0x24;
    public static final int VK_LEFT = 0x25;
    public static final int VK_UP = 0x26;
    public static final int VK_RIGHT = 0x27;
    public static final int VK_DOWN = 0x28;
    public static final int VK_SELECT = 0x29;
    public static final int VK_PRINT = 0x2A;
    public static final int VK_EXECUTE = 0x2B;
    public static final int VK_SNAPSHOT = 0x2C;
    public static final int VK_INSERT = 0x2D;
    public static final int VK_DELETE = 0x2E;
    public static final int VK_HELP = 0x2F;
    /*
     * VK_0 - VK_9 are the same as ASCII '0' - '9' (0x30 - 0x39)
     * 0x40 : unassigned
     * VK_A - VK_Z are the same as ASCII 'A' - 'Z' (0x41 - 0x5A)
     */
    public static final int VK_0 = 0x30;
    public static final int VK_1 = 0x31;
    public static final int VK_2 = 0x32;
    public static final int VK_3 = 0x33;
    public static final int VK_4 = 0x34;
    public static final int VK_5 = 0x35;
    public static final int VK_6 = 0x36;
    public static final int VK_7 = 0x37;
    public static final int VK_8 = 0x38;
    public static final int VK_9 = 0x39;

    public static final int VK_A = 0x41;
    public static final int VK_B = 0x42;
    public static final int VK_C = 0x43;
    public static final int VK_D = 0x44;
    public static final int VK_E = 0x45;
    public static final int VK_F = 0x46;
    public static final int VK_G = 0x47;
    public static final int VK_H = 0x48;
    public static final int VK_I = 0x49;
    public static final int VK_J = 0x4A;
    public static final int VK_K = 0x4B;
    public static final int VK_L = 0x4C;
    public static final int VK_M = 0x4D;
    public static final int VK_N = 0x4E;
    public static final int VK_O = 0x4F;
    public static final int VK_P = 0x50;
    public static final int VK_Q = 0x51;
    public static final int VK_R = 0x52;
    public static final int VK_S = 0x53;
    public static final int VK_T = 0x54;
    public static final int VK_U = 0x55;
    public static final int VK_V = 0x56;
    public static final int VK_W = 0x57;
    public static final int VK_X = 0x58;
    public static final int VK_Y = 0x59;
    public static final int VK_Z = 0x5A;

    public static final int VK_LWIN = 0x5B;
    public static final int VK_RWIN = 0x5C;
    public static final int VK_APPS = 0x5D;
    /*
     * 0x5E : reserved;
     */

    public static final int VK_SLEEP = 0x5F;

    public static final int VK_NUMPAD0 = 0x60;
    public static final int VK_NUMPAD1 = 0x61;
    public static final int VK_NUMPAD2 = 0x62;
    public static final int VK_NUMPAD3 = 0x63;
    public static final int VK_NUMPAD4 = 0x64;
    public static final int VK_NUMPAD5 = 0x65;
    public static final int VK_NUMPAD6 = 0x66;
    public static final int VK_NUMPAD7 = 0x67;
    public static final int VK_NUMPAD8 = 0x68;
    public static final int VK_NUMPAD9 = 0x69;
    public static final int VK_MULTIPLY = 0x6A;
    public static final int VK_ADD = 0x6B;
    public static final int VK_SEPARATOR = 0x6C;
    public static final int VK_SUBTRACT = 0x6D;
    public static final int VK_DECIMAL = 0x6E;
    public static final int VK_DIVIDE = 0x6F;
    public static final int VK_F1 = 0x70;
    public static final int VK_F2 = 0x71;
    public static final int VK_F3 = 0x72;
    public static final int VK_F4 = 0x73;
    public static final int VK_F5 = 0x74;
    public static final int VK_F6 = 0x75;
    public static final int VK_F7 = 0x76;
    public static final int VK_F8 = 0x77;
    public static final int VK_F9 = 0x78;
    public static final int VK_F10 = 0x79;
    public static final int VK_F11 = 0x7A;
    public static final int VK_F12 = 0x7B;
    public static final int VK_F13 = 0x7C;
    public static final int VK_F14 = 0x7D;
    public static final int VK_F15 = 0x7E;
    public static final int VK_F16 = 0x7F;
    public static final int VK_F17 = 0x80;
    public static final int VK_F18 = 0x81;
    public static final int VK_F19 = 0x82;
    public static final int VK_F20 = 0x83;
    public static final int VK_F21 = 0x84;
    public static final int VK_F22 = 0x85;
    public static final int VK_F23 = 0x86;
    public static final int VK_F24 = 0x87;

    /*
     * 0x88 - 0x8F : unassigned;
     */

    public static final int VK_NUMLOCK = 0x90;
    public static final int VK_SCROLL = 0x91;

    /*
     * NEC PC-9800 kbd definitions
     */
    public static final int VK_OEM_NEC_EQUAL = 0x92;   // '=' key on numpad
    /*
     * Fujitsu/OASYS kbd definitions
     */
    public static final int VK_OEM_FJ_JISHO = 0x92;   // 'Dictionary' key
    public static final int VK_OEM_FJ_MASSHOU = 0x93;   // 'Unregister word' key
    public static final int VK_OEM_FJ_TOUROKU = 0x94;   // 'Register word' key
    public static final int VK_OEM_FJ_LOYA = 0x95;   // 'Left OYAYUBI' key
    public static final int VK_OEM_FJ_ROYA = 0x96;   // 'Right OYAYUBI' key

    /*
     * 0x97 - 0x9F : unassigned
     */

    /*
     * VK_L* & VK_R* - left and right Alt, Ctrl and Shift virtual keys.
     * Used only as parameters to GetAsyncKeyState() and GetKeyState().
     * No other API or message will distinguish left and right keys in this way.
     */
    public static final int VK_LSHIFT = 0xA0;
    public static final int VK_RSHIFT = 0xA1;
    public static final int VK_LCONTROL = 0xA2;
    public static final int VK_RCONTROL = 0xA3;
    public static final int VK_LMENU = 0xA4;
    public static final int VK_RMENU = 0xA5;

    public static final int VK_BROWSER_BACK = 0xA6;
    public static final int VK_BROWSER_FORWARD = 0xA7;
    public static final int VK_BROWSER_REFRESH = 0xA8;
    public static final int VK_BROWSER_STOP = 0xA9;
    public static final int VK_BROWSER_SEARCH = 0xAA;
    public static final int VK_BROWSER_FAVORITES = 0xAB;
    public static final int VK_BROWSER_HOME = 0xAC;

    public static final int VK_VOLUME_MUTE = 0xAD;
    public static final int VK_VOLUME_DOWN = 0xAE;
    public static final int VK_VOLUME_UP = 0xAF;
    public static final int VK_MEDIA_NEXT_TRACK = 0xB0;
    public static final int VK_MEDIA_PREV_TRACK = 0xB1;
    public static final int VK_MEDIA_STOP = 0xB2;
    public static final int VK_MEDIA_PLAY_PAUSE = 0xB3;
    public static final int VK_LAUNCH_MAIL = 0xB4;
    public static final int VK_LAUNCH_MEDIA_SELECT = 0xB5;
    public static final int VK_LAUNCH_APP1 = 0xB6;
    public static final int VK_LAUNCH_APP2 = 0xB7;

    /*
     * 0xB8 - 0xB9 : reserved
     */

    public static final int VK_OEM_1 = 0xBA;   // ';:' for US
    public static final int VK_OEM_PLUS = 0xBB;   // '+' any country
    public static final int VK_OEM_COMMA = 0xBC;   // ',' any country
    public static final int VK_OEM_MINUS = 0xBD;   // '-' any country
    public static final int VK_OEM_PERIOD = 0xBE;   // '.' any country
    public static final int VK_OEM_2 = 0xBF;   // '/?' for US
    public static final int VK_OEM_3 = 0xC0;   // '`~' for US

    /*
     * 0xC1 - 0xD7 : reserved
     */

    /*
     * 0xD8 - 0xDA : unassigned
     */

    public static final int VK_OEM_4 = 0xDB;  //  '[{' for US
    public static final int VK_OEM_5 = 0xDC;  //  '\|' for US
    public static final int VK_OEM_6 = 0xDD;  //  ']}' for US
    public static final int VK_OEM_7 = 0xDE;  //  ''"' for US
    public static final int VK_OEM_8 = 0xDF;

    /*
     * 0xE0 : reserved
     */

    /*
     * Various extended or enhanced keyboards
     */
    public static final int VK_OEM_AX = 0xE1;  //  'AX' key on Japanese AX kbd
    public static final int VK_OEM_102 = 0xE2;  //  "<>" or "\|" on RT 102-key kbd.
    public static final int VK_ICO_HELP = 0xE3;  //  Help key on ICO
    public static final int VK_ICO_00 = 0xE4;  //  00 key on ICO

    public static final int VK_PROCESSKEY = 0xE5;

    public static final int VK_ICO_CLEAR = 0xE6;


    public static final int VK_PACKET = 0xE7;

    /*
     * 0xE8 : unassigned
     */

    /*
     * Nokia/Ericsson definitions
     */
    public static final int VK_OEM_RESET = 0xE9;
    public static final int VK_OEM_JUMP = 0xEA;
    public static final int VK_OEM_PA1 = 0xEB;
    public static final int VK_OEM_PA2 = 0xEC;
    public static final int VK_OEM_PA3 = 0xED;
    public static final int VK_OEM_WSCTRL = 0xEE;
    public static final int VK_OEM_CUSEL = 0xEF;
    public static final int VK_OEM_ATTN = 0xF0;
    public static final int VK_OEM_FINISH = 0xF1;
    public static final int VK_OEM_COPY = 0xF2;
    public static final int VK_OEM_AUTO = 0xF3;
    public static final int VK_OEM_ENLW = 0xF4;
    public static final int VK_OEM_BACKTAB = 0xF5;

    public static final int VK_ATTN = 0xF6;
    public static final int VK_CRSEL = 0xF7;
    public static final int VK_EXSEL = 0xF8;
    public static final int VK_EREOF = 0xF9;
    public static final int VK_PLAY = 0xFA;
    public static final int VK_ZOOM = 0xFB;
    public static final int VK_NONAME = 0xFC;
    public static final int VK_PA1 = 0xFD;
    public static final int VK_OEM_CLEAR = 0xFE;
}