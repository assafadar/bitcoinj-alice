package org.bitcoinj.core;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by over on 09.12.14.
 */
public class BitcointVerifyFunction implements VerifyFunction, Serializable{

    private static final long serialVersionUID = -4137684978866266729L;

    @Override
    public boolean verify(Block block, boolean throwException) {
        // This part is key - it is what proves the block was as difficult to make as it claims
        // to be. Note however that in the context of this function, the block can claim to be
        // as difficult as it wants to be .... if somebody was able to take control of our network
        // connection and fork us onto a different chain, they could send us valid blocks with
        // ridiculously easy difficulty and this function would accept them.
        //
        // To prevent this attack from being possible, elsewhere we check that the difficultyTarget
        // field is of the right value. This requires us to have the preceeding blocks.
        BigInteger target = block.getDifficultyTargetAsInteger();

        BigInteger h = block.getHash().toBigInteger();
        if (h.compareTo(target) > 0) {
            // Proof of work check failed!
            if (throwException)
                throw new VerificationException("Hash is higher than target: " + block.getHashAsString() + " vs "
                        + target.toString(16));
            else
                return false;
        }
        return true;
    }
}
