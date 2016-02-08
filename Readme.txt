Hello reader! Please do not be alarmed if any files (especially the readme) change. This is a first in some ways for myself.

This application is for a customization of Double Metaphone. My team and I found out about this algorithm via search,
and decided to implement a variation for it for word filtering. Another search later and we discovered the following source:
https://github.com/android/platform_external_apache-http/blob/master/src/org/apache/commons/codec/language/DoubleMetaphone.java
This source, retrieved February 7th, 2016, comes with the following notice, reproduced here.

/*
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
The license will be placed on the corresponding wiki page for safekeeping should the link become abandoned.
 
Please do note that, aside from the wrapper (MainActivity) and the change in imports, the initial commit represents the 
original code.
Modification after that is under the ownership of myself (and/or whomever of my team works on it) and can be viewed via the 
commit history on Github at:
https://github.com/BrennenFagan/DoubleMetaphone

We will comment on any changes we make to the code itself, especially should it be in the middle of existing code.
