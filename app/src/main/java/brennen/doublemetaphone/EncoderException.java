//Taken from https://github.com/android/platform_external_apache-http/blob/master/src/org/apache/commons/codec/language/DoubleMetaphone.java

package brennen.doublemetaphone;

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

/**
 * Thrown when there is a failure condition during the encoding process.  This
 * exception is thrown when an Encoder encounters a encoding specific exception
 * such as invalid data, inability to calculate a checksum, characters outside of the
 * expected range.
 *
 * @author Apache Software Foundation
 * @version $Id: EncoderException.java,v 1.10 2004/02/29 04:08:31 tobrien Exp $
 *
 * @deprecated Please use {@link java.net.URL#openConnection} instead.
 *     Please visit <a href="http://android-developers.blogspot.com/2011/09/androids-http-clients.html">this webpage</a>
 *     for further details.
 */
@Deprecated
public class EncoderException extends Exception {

    /**
     * Creates a new instance of this exception with an useful message.
     *
     * @param pMessage a useful message relating to the encoder specific error.
     */
    public EncoderException(String pMessage) {
        super(pMessage);
    }
}


