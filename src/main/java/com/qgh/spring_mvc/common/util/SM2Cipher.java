package com.qgh.spring_mvc.common.util;

/**
 * @author 秦光泓
 * @title:SM2加解密
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/1419:35
 */
public class SM2Cipher {
   /* private int ct = 1;

                  private ECPoint p2;
          private SM3Digest sm3keybase;
          private SM3Digest sm3c3;

                 private byte[] key = new byte[32];
         private byte keyOff = 0;

                public SM2Cipher() { }



                 private void Reset()
         {
                   sm3keybase = new SM3Digest();
                    sm3c3 = new SM3Digest();

                    byte[] p;
                    p = p2.X.ToBigInteger().ToByteArrayUnsigned();
                    sm3keybase.BlockUpdate(p, 0, p.Length);
                    sm3c3.BlockUpdate(p, 0, p.Length);

                    p = p2.Y.ToBigInteger().ToByteArrayUnsigned();
                    sm3keybase.BlockUpdate(p, 0, p.Length);

                    ct = 1;
                    NextKey();
                }

                 private void NextKey()
         {
                     sm3keycur.Update((byte)(ct >> 24 & 0x00ff));
                    sm3keycur.Update((byte)(ct >> 16 & 0x00ff));
                   sm3keycur.Update((byte)(ct >> 8 & 0x00ff));
                    sm3keycur.Update((byte)(ct & 0x00ff));
                   sm3keycur.DoFinal(key, 0);
                    keyOff = 0;
                    ct++;
                }

                 public virtual ECPoint InitEncipher(ECPoint userKey)
         {
                   BigInteger k = null;
                    ECPoint c1 = null;

                    if (1==1)
                        {
                            AsymmetricCipherKeyPair key = SM2CryptoServiceProvider.SM2KeyPairGenerator.GenerateKeyPair();
                            ECPrivateKeyParameters ecpriv = (ECPrivateKeyParameters)key.Private;
                            ECPublicKeyParameters ecpub = (ECPublicKeyParameters)key.Public;

                            k = ecpriv.D;
                           c1 = ecpub.Q;
                        }

                    p2 = userKey.Multiply(k);
                    Reset();
                    return c1;

                }

         68        public virtual void Encrypt(byte[] data)
         {
        70            sm3c3.BlockUpdate(data, 0, data.Length);
        71            for (int i = 0; i < data.Length; i++)
            72            {
            73                if (keyOff == key.Length)
                                    NextKey();

            76                data[i] ^= key[keyOff++];
                       }
                }
 79
         80        public virtual void InitDecipher(BigInteger userD, ECPoint c1)
 81        {
        82            p2 = c1.Multiply(userD);
        83            Reset();
        84        }
 85
         86        public virtual void Decrypt(byte[] data)
 87        {
        88            for (int i = 0; i < data.Length; i++)
            89            {
            90                if (keyOff == key.Length)
                91                    NextKey();
            92
            93                data[i] ^= key[keyOff++];
            94            }
        95            sm3c3.BlockUpdate(data, 0, data.Length);
        96        }
 97
         98        public virtual void Dofinal(byte[] c3)
 99        {
        100            byte[] p = p2.Y.ToBigInteger().ToByteArrayUnsigned();
        101            sm3c3.BlockUpdate(p, 0, p.Length);
        102            sm3c3.DoFinal(c3, 0);
        103            Reset();
        104        }
105
        106
        107        /// <summary>
        108        /// 使用SM2公钥加密数据
        109        /// </summary>
        110        /// <param name="pubKey"></param>
        111        /// <param name="plaintext"></param>
        112        /// <returns></returns>
        113        public string Encrypt(ECPoint pubKey, byte[] plaintext)
114        {
        115
        116            byte[] data = new byte[plaintext.Length];
        117            Array.Copy(plaintext, data, plaintext.Length);
        118
        119            ECPoint c1 = InitEncipher(pubKey);
        120            Encrypt(data);
        121
        122            byte[] c3 = new byte[32];
        123            Dofinal(c3);
        124
        125            string hexString = c1.X.ToBigInteger().ToString(16) + c1.Y.ToBigInteger().ToString(16)
        126                + Org.BouncyCastle.Utilities.Encoders.Hex.ToHexString(data)
        127                + Org.BouncyCastle.Utilities.Encoders.Hex.ToHexString(c3);
        128
        129            return hexString;
        130
        131        }
132
        133        /// <summary>
        134        /// 使用SM2解密数据
        135        /// </summary>
        136        /// <param name="privateKey"></param>
        137        /// <param name="ciphertext"></param>
        138        /// <returns></returns>
        139        public byte[] Decrypt(BigInteger privateKey, string ciphertext)
140        {
        141
        142            string hexString = ciphertext;
        143            string c1X = hexString.Substring(0, 64);
        144            string c1Y = hexString.Substring(0 + c1X.Length, 64);
        145            string encrypData = hexString.Substring(c1X.Length + c1Y.Length, hexString.Length - c1X.Length - c1Y.Length - 64);
        146            string c3 = hexString.Substring(hexString.Length - 64);
        147
        148            byte[] data = SM2CryptoServiceProvider.StrToToHexByte(encrypData);
        149
        150            ECPoint c1 = CreatePoint(c1X, c1Y);
        151
        152            InitDecipher(privateKey, c1);
        153            Decrypt(data);
        154
                    byte[] c3_ = new byte[32];
                  Dofinal(c3_);

                    string decryptData = Encoding.Default.GetString(data);
                   bool isDecrypt = Org.BouncyCastle.Utilities.Encoders.Hex.ToHexString(c3_) == c3;

                    return (isDecrypt ? data : new byte[0]);
                }


                /// <summary>
                /// 创建坐标点
                /// </summary>
                /// <param name="x"></param>
                /// <param name="y"></param>
                /// <returns></returns>
                public static ECPoint CreatePoint(string x, string y)
        {

                    BigInteger biX = new BigInteger(x, 16);
                    BigInteger biY = new BigInteger(y, 16);
                    ECFieldElement fx = new FpFieldElement(SM2CryptoServiceProvider.ecc_p, biX);
                    ECFieldElement fy = new FpFieldElement(SM2CryptoServiceProvider.ecc_p, biY);
                    ECPoint point = new FpPoint(SM2CryptoServiceProvider.ecc_curve, fx,fy);
                    return point;
                }*/

}
