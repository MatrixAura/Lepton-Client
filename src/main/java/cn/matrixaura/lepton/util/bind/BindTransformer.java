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

import org.lwjgl.input.Keyboard;

import static cn.matrixaura.lepton.util.bind.WindowsKeycodes.*;

@SuppressWarnings("unused")
public class BindTransformer {

    public static int stand2lwjgl(int standKey) {
        switch (standKey) {
            case VK_ESCAPE:
                return Keyboard.KEY_ESCAPE;
            case VK_1:
                return Keyboard.KEY_1;
            case VK_2:
                return Keyboard.KEY_2;
            case VK_3:
                return Keyboard.KEY_3;
            case VK_4:
                return Keyboard.KEY_4;
            case VK_5:
                return Keyboard.KEY_5;
            case VK_6:
                return Keyboard.KEY_6;
            case VK_7:
                return Keyboard.KEY_7;
            case VK_8:
                return Keyboard.KEY_8;
            case VK_9:
                return Keyboard.KEY_9;
            case VK_0:
                return Keyboard.KEY_0;
            case VK_OEM_MINUS:
                return Keyboard.KEY_MINUS;
            case VK_OEM_PLUS:
                return Keyboard.KEY_EQUALS;
            case VK_BACK:
                return Keyboard.KEY_BACK;
            case VK_TAB:
                return Keyboard.KEY_TAB;
            case VK_Q:
                return Keyboard.KEY_Q;
            case VK_W:
                return Keyboard.KEY_W;
            case VK_E:
                return Keyboard.KEY_E;
            case VK_R:
                return Keyboard.KEY_R;
            case VK_T:
                return Keyboard.KEY_T;
            case VK_Y:
                return Keyboard.KEY_Y;
            case VK_U:
                return Keyboard.KEY_U;
            case VK_I:
                return Keyboard.KEY_I;
            case VK_O:
                return Keyboard.KEY_O;
            case VK_P:
                return Keyboard.KEY_P;
            case VK_OEM_4:
                return Keyboard.KEY_LBRACKET;
            case VK_OEM_6:
                return Keyboard.KEY_RBRACKET;
            case VK_RETURN:
                return Keyboard.KEY_RETURN;
            case VK_LCONTROL:
                return Keyboard.KEY_LCONTROL;
            case VK_A:
                return Keyboard.KEY_A;
            case VK_S:
                return Keyboard.KEY_S;
            case VK_D:
                return Keyboard.KEY_D;
            case VK_F:
                return Keyboard.KEY_F;
            case VK_G:
                return Keyboard.KEY_G;
            case VK_H:
                return Keyboard.KEY_H;
            case VK_J:
                return Keyboard.KEY_J;
            case VK_K:
                return Keyboard.KEY_K;
            case VK_L:
                return Keyboard.KEY_L;
            case VK_OEM_1:
                return Keyboard.KEY_SEMICOLON;
            case VK_OEM_7:
                return Keyboard.KEY_APOSTROPHE;
            case VK_OEM_3:
            case VK_OEM_8:
                return Keyboard.KEY_GRAVE;
            case VK_LSHIFT:
                return Keyboard.KEY_LSHIFT;
            case VK_OEM_5:
                return Keyboard.KEY_BACKSLASH;
            case VK_Z:
                return Keyboard.KEY_Z;
            case VK_X:
                return Keyboard.KEY_X;
            case VK_C:
                return Keyboard.KEY_C;
            case VK_V:
                return Keyboard.KEY_V;
            case VK_B:
                return Keyboard.KEY_B;
            case VK_N:
                return Keyboard.KEY_N;
            case VK_M:
                return Keyboard.KEY_M;
            case VK_OEM_COMMA:
                return Keyboard.KEY_COMMA;
            case VK_OEM_PERIOD:
                return Keyboard.KEY_PERIOD;
            case VK_OEM_2:
                return Keyboard.KEY_SLASH;
            case VK_RSHIFT:
                return Keyboard.KEY_RSHIFT;
            case VK_MULTIPLY:
                return Keyboard.KEY_MULTIPLY;
            case VK_LMENU:
                return Keyboard.KEY_LMENU;
            case VK_SPACE:
                return Keyboard.KEY_SPACE;
            case VK_CAPITAL:
                return Keyboard.KEY_CAPITAL;
            case VK_F1:
                return Keyboard.KEY_F1;
            case VK_F2:
                return Keyboard.KEY_F2;
            case VK_F3:
                return Keyboard.KEY_F3;
            case VK_F4:
                return Keyboard.KEY_F4;
            case VK_F5:
                return Keyboard.KEY_F5;
            case VK_F6:
                return Keyboard.KEY_F6;
            case VK_F7:
                return Keyboard.KEY_F7;
            case VK_F8:
                return Keyboard.KEY_F8;
            case VK_F9:
                return Keyboard.KEY_F9;
            case VK_F10:
                return Keyboard.KEY_F10;
            case VK_NUMLOCK:
                return Keyboard.KEY_NUMLOCK;
            case VK_SCROLL:
                return Keyboard.KEY_SCROLL;
            case VK_NUMPAD7:
                return Keyboard.KEY_NUMPAD7;
            case VK_NUMPAD8:
                return Keyboard.KEY_NUMPAD8;
            case VK_NUMPAD9:
                return Keyboard.KEY_NUMPAD9;
            case VK_SUBTRACT:
                return Keyboard.KEY_SUBTRACT;
            case VK_NUMPAD4:
                return Keyboard.KEY_NUMPAD4;
            case VK_NUMPAD5:
                return Keyboard.KEY_NUMPAD5;
            case VK_NUMPAD6:
                return Keyboard.KEY_NUMPAD6;
            case VK_ADD:
                return Keyboard.KEY_ADD;
            case VK_NUMPAD1:
                return Keyboard.KEY_NUMPAD1;
            case VK_NUMPAD2:
                return Keyboard.KEY_NUMPAD2;
            case VK_NUMPAD3:
                return Keyboard.KEY_NUMPAD3;
            case VK_NUMPAD0:
                return Keyboard.KEY_NUMPAD0;
            case VK_DECIMAL:
                return Keyboard.KEY_DECIMAL;
            case VK_F11:
                return Keyboard.KEY_F11;
            case VK_F12:
                return Keyboard.KEY_F12;
            case VK_F13:
                return Keyboard.KEY_F13;
            case VK_F14:
                return Keyboard.KEY_F14;
            case VK_F15:
                return Keyboard.KEY_F15;
            case VK_KANA:
                return Keyboard.KEY_KANA;
            case VK_CONVERT:
                return Keyboard.KEY_CONVERT;
            case VK_NONCONVERT:
                return Keyboard.KEY_NOCONVERT;
            case VK_KANJI:
                return Keyboard.KEY_KANJI;
            case VK_RCONTROL:
                return Keyboard.KEY_RCONTROL;
            case VK_SEPARATOR:
                return Keyboard.KEY_NUMPADCOMMA;
            case VK_DIVIDE:
                return Keyboard.KEY_DIVIDE;
            case VK_SNAPSHOT:
                return Keyboard.KEY_SYSRQ;
            case VK_RMENU:
                return Keyboard.KEY_RMENU;
            case VK_PAUSE:
                return Keyboard.KEY_PAUSE;
            case VK_HOME:
                return Keyboard.KEY_HOME;
            case VK_UP:
                return Keyboard.KEY_UP;
            case VK_PRIOR:
                return Keyboard.KEY_PRIOR;
            case VK_LEFT:
                return Keyboard.KEY_LEFT;
            case VK_RIGHT:
                return Keyboard.KEY_RIGHT;
            case VK_END:
                return Keyboard.KEY_END;
            case VK_DOWN:
                return Keyboard.KEY_DOWN;
            case VK_NEXT:
                return Keyboard.KEY_NEXT;
            case VK_INSERT:
                return Keyboard.KEY_INSERT;
            case VK_DELETE:
                return Keyboard.KEY_DELETE;
            case VK_LWIN:
                return Keyboard.KEY_LMETA;
            case VK_RWIN:
                return Keyboard.KEY_RMETA;
            case VK_APPS:
                return Keyboard.KEY_APPS;
            case VK_SLEEP:
                return Keyboard.KEY_SLEEP;
            default:
                return Keyboard.KEY_NONE;
        }
    }

    public static int lwjgl2stand(int lwjglKey) {
        switch (lwjglKey) {
            case Keyboard.KEY_ESCAPE:
                return VK_ESCAPE;
            case Keyboard.KEY_1:
                return VK_1;
            case Keyboard.KEY_2:
                return VK_2;
            case Keyboard.KEY_3:
                return VK_3;
            case Keyboard.KEY_4:
                return VK_4;
            case Keyboard.KEY_5:
                return VK_5;
            case Keyboard.KEY_6:
                return VK_6;
            case Keyboard.KEY_7:
                return VK_7;
            case Keyboard.KEY_8:
                return VK_8;
            case Keyboard.KEY_9:
                return VK_9;
            case Keyboard.KEY_0:
                return VK_0;
            case Keyboard.KEY_MINUS:
                return VK_OEM_MINUS;
            case Keyboard.KEY_EQUALS:
                return VK_OEM_PLUS;
            case Keyboard.KEY_BACK:
                return VK_BACK;
            case Keyboard.KEY_TAB:
                return VK_TAB;
            case Keyboard.KEY_Q:
                return VK_Q;
            case Keyboard.KEY_W:
                return VK_W;
            case Keyboard.KEY_E:
                return VK_E;
            case Keyboard.KEY_R:
                return VK_R;
            case Keyboard.KEY_T:
                return VK_T;
            case Keyboard.KEY_Y:
                return VK_Y;
            case Keyboard.KEY_U:
                return VK_U;
            case Keyboard.KEY_I:
                return VK_I;
            case Keyboard.KEY_O:
                return VK_O;
            case Keyboard.KEY_P:
                return VK_P;
            case Keyboard.KEY_LBRACKET:
                return VK_OEM_4;
            case Keyboard.KEY_RBRACKET:
                return VK_OEM_6;
            case Keyboard.KEY_RETURN:
                return VK_RETURN;
            case Keyboard.KEY_LCONTROL:
                return VK_LCONTROL;
            case Keyboard.KEY_A:
                return VK_A;
            case Keyboard.KEY_S:
                return VK_S;
            case Keyboard.KEY_D:
                return VK_D;
            case Keyboard.KEY_F:
                return VK_F;
            case Keyboard.KEY_G:
                return VK_G;
            case Keyboard.KEY_H:
                return VK_H;
            case Keyboard.KEY_J:
                return VK_J;
            case Keyboard.KEY_K:
                return VK_K;
            case Keyboard.KEY_L:
                return VK_L;
            case Keyboard.KEY_SEMICOLON:
                return VK_OEM_1;
            case Keyboard.KEY_APOSTROPHE:
                return VK_OEM_7;
            case Keyboard.KEY_GRAVE:
                return VK_OEM_3;
            case Keyboard.KEY_LSHIFT:
                return VK_LSHIFT;
            case Keyboard.KEY_BACKSLASH:
                return VK_OEM_5;
            case Keyboard.KEY_Z:
                return VK_Z;
            case Keyboard.KEY_X:
                return VK_X;
            case Keyboard.KEY_C:
                return VK_C;
            case Keyboard.KEY_V:
                return VK_V;
            case Keyboard.KEY_B:
                return VK_B;
            case Keyboard.KEY_N:
                return VK_N;
            case Keyboard.KEY_M:
                return VK_M;
            case Keyboard.KEY_COMMA:
                return VK_OEM_COMMA;
            case Keyboard.KEY_PERIOD:
                return VK_OEM_PERIOD;
            case Keyboard.KEY_SLASH:
                return VK_OEM_2;
            case Keyboard.KEY_RSHIFT:
                return VK_RSHIFT;
            case Keyboard.KEY_MULTIPLY:
                return VK_MULTIPLY;
            case Keyboard.KEY_LMENU:
                return VK_LMENU;
            case Keyboard.KEY_SPACE:
                return VK_SPACE;
            case Keyboard.KEY_CAPITAL:
                return VK_CAPITAL;
            case Keyboard.KEY_F1:
                return VK_F1;
            case Keyboard.KEY_F2:
                return VK_F2;
            case Keyboard.KEY_F3:
                return VK_F3;
            case Keyboard.KEY_F4:
                return VK_F4;
            case Keyboard.KEY_F5:
                return VK_F5;
            case Keyboard.KEY_F6:
                return VK_F6;
            case Keyboard.KEY_F7:
                return VK_F7;
            case Keyboard.KEY_F8:
                return VK_F8;
            case Keyboard.KEY_F9:
                return VK_F9;
            case Keyboard.KEY_F10:
                return VK_F10;
            case Keyboard.KEY_NUMLOCK:
                return VK_NUMLOCK;
            case Keyboard.KEY_SCROLL:
                return VK_SCROLL;
            case Keyboard.KEY_NUMPAD7:
                return VK_NUMPAD7;
            case Keyboard.KEY_NUMPAD8:
                return VK_NUMPAD8;
            case Keyboard.KEY_NUMPAD9:
                return VK_NUMPAD9;
            case Keyboard.KEY_SUBTRACT:
                return VK_SUBTRACT;
            case Keyboard.KEY_NUMPAD4:
                return VK_NUMPAD4;
            case Keyboard.KEY_NUMPAD5:
                return VK_NUMPAD5;
            case Keyboard.KEY_NUMPAD6:
                return VK_NUMPAD6;
            case Keyboard.KEY_ADD:
                return VK_ADD;
            case Keyboard.KEY_NUMPAD1:
                return VK_NUMPAD1;
            case Keyboard.KEY_NUMPAD2:
                return VK_NUMPAD2;
            case Keyboard.KEY_NUMPAD3:
                return VK_NUMPAD3;
            case Keyboard.KEY_NUMPAD0:
                return VK_NUMPAD0;
            case Keyboard.KEY_DECIMAL:
                return VK_DECIMAL;
            case Keyboard.KEY_F11:
                return VK_F11;
            case Keyboard.KEY_F12:
                return VK_F12;
            case Keyboard.KEY_F13:
                return VK_F13;
            case Keyboard.KEY_F14:
                return VK_F14;
            case Keyboard.KEY_F15:
                return VK_F15;
            case Keyboard.KEY_KANA:
                return VK_KANA;
            case Keyboard.KEY_CONVERT:
                return VK_CONVERT;
            case Keyboard.KEY_NOCONVERT:
                return VK_NONCONVERT;
            case Keyboard.KEY_KANJI:
                return VK_KANJI;
            case Keyboard.KEY_RCONTROL:
                return VK_RCONTROL;
            case Keyboard.KEY_NUMPADCOMMA:
                return VK_SEPARATOR;
            case Keyboard.KEY_DIVIDE:
                return VK_DIVIDE;
            case Keyboard.KEY_SYSRQ:
                return VK_SNAPSHOT;
            case Keyboard.KEY_RMENU:
                return VK_RMENU;
            case Keyboard.KEY_PAUSE:
                return VK_PAUSE;
            case Keyboard.KEY_HOME:
                return VK_HOME;
            case Keyboard.KEY_UP:
                return VK_UP;
            case Keyboard.KEY_PRIOR:
                return VK_PRIOR;
            case Keyboard.KEY_LEFT:
                return VK_LEFT;
            case Keyboard.KEY_RIGHT:
                return VK_RIGHT;
            case Keyboard.KEY_END:
                return VK_END;
            case Keyboard.KEY_DOWN:
                return VK_DOWN;
            case Keyboard.KEY_NEXT:
                return VK_NEXT;
            case Keyboard.KEY_INSERT:
                return VK_INSERT;
            case Keyboard.KEY_DELETE:
                return VK_DELETE;
            case Keyboard.KEY_LMETA:
                return VK_LWIN;
            case Keyboard.KEY_RMETA:
                return VK_RWIN;
            case Keyboard.KEY_APPS:
                return VK_APPS;
            case Keyboard.KEY_SLEEP:
                return VK_SLEEP;
            default:
                return VK_NONE;
        }
    }

}
