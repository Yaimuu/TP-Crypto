import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class unittests {

	@Test
	void test_Vigenere_Encode() {
		Crypto vigenere = new Vigenere("GHDJ");
		assertEquals(vigenere.encode("CeciEstUnTestDEncode"), "IlfrKzwDtAhbzKHwivgn");
	}
	@Test
	void test_Vigenere_Decode() {
		Crypto vigenere = new Vigenere("YANIS");
		assertEquals(vigenere.decode("AepqWqtHvLcsgLWlcblw"), "CeciEstUnTestDEncode");
	}
	
	@Test
	void test_Caesar_Encode() {
		Crypto caesar = new Ceeeeaaaasaaaaaarrr(15);
		assertEquals(caesar.encode("CeciEstUnTestDEncode"), "RtrxThiJcIthiSTcrdst");
	}
	@Test
	void test_Caesar_Decode() {
		Crypto caesar = new Ceeeeaaaasaaaaaarrr(15);
		assertEquals(caesar.decode("RtrxThiJcIthiSTcrdst"), "CeciEstUnTestDEncode");
	}
	
	@Test
	void test_XOR_Encode() {
		Crypto xor = new Xor("GHDJ");
		String encoded_message = xor.encode("CeciEstUnTestDEncode");
		assertEquals(xor.encode("CeciEstUnTestDEncode"), encoded_message);
	}
	@Test
	void test_XOR_Decode() {
		Crypto xor = new Xor("YANIS");
		assertEquals(xor.decode(xor.encode("CeciEstUnTestDEncode")), "CeciEstUnTestDEncode");
	}
	
	@Test
	void test_Dico_Encode() {
		Crypto dico = new Dico("AZBWCXDYEFGHIJKLMONPQTRSVU");
		assertEquals(dico.encode("CeciEstUnTestDEncode"), "BcbeCnpQjPcnpWCjbkwc");
	}
	@Test
	void test_Dico_Decode() {
		Crypto dico = new Dico("AZBWCXDYEFGHIJKLMONPQTRSVU");
		assertEquals(dico.decode("BcbeCnpQjPcnpWCjbkwc"), "CeciEstUnTestDEncode");
	}

}
