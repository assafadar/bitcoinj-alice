package org.bitcoinj.core;

import java.math.BigInteger;

/**
 * Created by over on 09.12.14.
 */
public interface VerifyFunction {
   boolean verify(Block block, boolean throwException) throws VerificationException;
}
