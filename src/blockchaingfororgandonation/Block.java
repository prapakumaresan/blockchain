/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchaingfororgandonation;

import java.security.SignatureException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Block 
{
    public int blocknumber;
    public String Timestamp;
    public int nonce;
    public String data;
    public String previousBlockHash;    

    public String hash;
    
    HashGeneration sg=new HashGeneration();     // HmacSHA256 hash generation

    public Block(int blocknumber, String Timestamp, int nonce, String data, String previousBlockHash) {

        this.blocknumber=blocknumber;
        this.Timestamp=Timestamp;
        this.nonce=nonce;
        this.data = data;
        this.previousBlockHash = previousBlockHash;

        // We would input the current block’s number, the timestamp, the nonce number, the data stored in the block 
        // and the hash of the previous block into the SHA256 function to get the value of the current block’s hash
        // SHA256(Block Number, Timestamp, Nonce, Data, Prev. Block’s Hash) -> Hash
        // Reference Link: https://medium.com/swlh/how-does-bitcoin-blockchain-mining-work-36db1c5cb55d  (See: Part 3: Block configuration)
        
        String input=blocknumber+"#"+Timestamp+"#"+nonce+"#"+previousBlockHash;     // data only provided in next line
        
        try
        {        
            this.hash  = sg.generateHash(data,input);   // data provided here
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static class HashGeneration 
    {
	private static final String HASH_ALGORITHM = "HmacSHA256";

	public static String generateHash(String data, String key) throws java.security.SignatureException
	{
            String hash;
            try 
            {                
                SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HASH_ALGORITHM);               
                Mac mac = Mac.getInstance(HASH_ALGORITHM);
                mac.init(signingKey);               
                byte[] rawHmac = mac.doFinal(data.getBytes());               
                hash = new String(encode(rawHmac));
            }
            catch (Exception e) 
            {
                throw new SignatureException("Failed to generate hash : " + e.getMessage());
            }
            return hash;
	}
		
	private static char[] encode(byte[] bytes) 
        {
            final int amount = bytes.length;
            char[] result = new char[2 * amount];

            int j = 0;
            for (int i = 0; i < amount; i++) {
              result[j++] = HEX[(0xF0 & bytes[i]) >>> 4];
              result[j++] = HEX[(0x0F & bytes[i])];
            }
            return result;
        }

        private static final char[] HEX = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };
    }
}