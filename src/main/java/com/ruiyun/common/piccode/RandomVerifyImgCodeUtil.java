package com.ruiyun.common.piccode;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

/**
 * 验证码工具类： 随机字体、字体样式、字体大小（验证码图片宽度 - 8 ~ 验证码图片宽度 + 10） 彩色字符 每个字符的颜色随机，一定会不相同 随机字符
 * 阿拉伯数字 + 小写字母 + 大写字母 3D中空自定义字体，需要单独使用，只有阿拉伯数字和大写字母
 * 
 * @author cgtu
 * @date 2017年5月9日 下午7:27:55
 */
public class RandomVerifyImgCodeUtil {
	private static Logger logger = Logger.getLogger(RandomVerifyImgCodeUtil.class);
	/**
	 * 随机类
	 */
	private static Random random = new Random();

	// 放到session中的key
	public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";

	// 验证码来源范围，去掉了0,1,I,O,l,o几个容易混淆的字符
	public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";

	private static ImgFontByte imgFontByte = new ImgFontByte();

	private static Font baseFont;
	static {
		try {
			baseFont = Font.createFont(Font.TRUETYPE_FONT,
					new ByteArrayInputStream(imgFontByte.hex2byte(imgFontByte.getFontByteStr())));
		} catch (FontFormatException e) {
			logger.error("new img font font format failed. e: " + e.getMessage(), e);
		} catch (IOException e) {
			logger.error("new img font io failed. e: " + e.getMessage(), e);
		}
	}

	// 字体类型
	private static String[] fontName = { "Algerian", "Arial", "Arial Black", "Agency FB", "Calibri", "Cambria",
			"Gadugi", "Georgia", "Consolas", "Comic Sans MS", "Courier New", "Gill sans", "Time News Roman", "Tahoma",
			"Quantzite", "Verdana" };

	// 字体样式
	private static int[] fontStyle = { Font.BOLD, Font.ITALIC, Font.ROMAN_BASELINE, Font.PLAIN,
			Font.BOLD + Font.ITALIC };

	// 颜色
	private static Color[] colorRange = { Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA,
			Color.ORANGE, Color.PINK, Color.YELLOW, Color.GREEN, Color.BLUE, Color.DARK_GRAY, Color.BLACK, Color.RED };

	/**
	 * 使用系统默认字符源生成验证码
	 * 
	 * @param verifySize
	 *            验证码长度
	 * @param uuid
	 *            App端传的参数，app没有session和cookie，必须用设备号在redis记录图形验证码的值
	 * @param platFormName
	 *            平台名称：pc\wap\app app比较特殊，需要单独处理
	 * @param request
	 *            请求
	 * @return
	 */
	public static String generateVerifyCode(int verifySize) {
		return generateVerifyCode(verifySize, VERIFY_CODES);
	}

	/**
	 * 使用指定源生成验证码
	 * 
	 * @param verifySize
	 *            验证码长度
	 * @param sources
	 *            验证码字符源
	 * @return
	 */
	private static String generateVerifyCode(int verifySize, String sources) {
		if (sources == null || sources.length() == 0) {
			sources = VERIFY_CODES;
		}
		int codesLen = sources.length();
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder verifyCode = new StringBuilder(verifySize);
		for (int i = 0; i < verifySize; i++) {
			verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
		}

		return verifyCode.toString();
	}

	/**
	 * 输出指定验证码图片流
	 * 
	 * @param w
	 *            验证码图片的宽
	 * @param h
	 *            验证码图片的高
	 * @param os
	 *            流
	 * @param code
	 *            验证码
	 * @param type
	 *            场景类型，login：登录， coupons：领券 登录清晰化，领券模糊化 3D: 3D中空自定义字体
	 *            GIF：普通动态GIF GIF3D：3D动态GIF mix2: 普通字体和3D字体混合 mixGIF: 混合动态GIF
	 * @throws IOException
	 */
	public static void outputImage(int w, int h, OutputStream os, String code, String type) throws IOException {
		int verifySize = code.length();
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Random rand = new Random();
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color[] colors = new Color[5];
		Color[] colorSpaces = colorRange;
		float[] fractions = new float[colors.length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
			fractions[i] = rand.nextFloat();
		}
		Arrays.sort(fractions);

		g2.setColor(Color.GRAY);// 设置边框色
		g2.fillRect(0, 0, w, h);

		Color c = getRandColor(200, 250);
		g2.setColor(c);// 设置背景色
		g2.fillRect(0, 2, w, h - 4);

		char[] charts = code.toCharArray();
		for (int i = 0; i < charts.length; i++) {
			g2.setColor(c);// 设置背景色
			g2.setFont(getRandomFont(h, type));
			g2.fillRect(0, 2, w, h - 4);
		}

		// 1.绘制干扰线
		Random random = new Random();
		g2.setColor(getRandColor(160, 200));// 设置线条的颜色
		int lineNumbers = 20;
		if (type.equals("login") || type.contains("mix") || type.contains("3D")) {
			lineNumbers = 20;
		} else if (type.equals("coupons")) {
			lineNumbers = getRandomDrawLine();
		} else {
			lineNumbers = getRandomDrawLine();
		}
		for (int i = 0; i < lineNumbers; i++) {
			int x = random.nextInt(w - 1);
			int y = random.nextInt(h - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g2.drawLine(x, y, x + xl + 40, y + yl + 20);
		}

		// 2.添加噪点
		float yawpRate = 0.05f;
		if (type.equals("login") || type.contains("mix") || type.contains("3D")) {
			yawpRate = 0.05f; // 噪声率
		} else if (type.equals("coupons")) {
			yawpRate = getRandomDrawPoint(); // 噪声率
		} else {
			yawpRate = getRandomDrawPoint(); // 噪声率
		}
		int area = (int) (yawpRate * w * h);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(w);
			int y = random.nextInt(h);
			int rgb = getRandomIntColor();
			image.setRGB(x, y, rgb);
		}

		// 3.使图片扭曲
		shear(g2, w, h, c);

		char[] chars = code.toCharArray();
		Double rd = rand.nextDouble();
		Boolean rb = rand.nextBoolean();

		if (type.equals("login")) {
			for (int i = 0; i < verifySize; i++) {
				g2.setColor(getRandColor(100, 160));
				g2.setFont(getRandomFont(h, type));

				AffineTransform affine = new AffineTransform();
				affine.setToRotation(Math.PI / 4 * rd * (rb ? 1 : -1), (w / verifySize) * i + (h - 4) / 2, h / 2);
				g2.setTransform(affine);
				g2.drawOval(random.nextInt(w), random.nextInt(h), 5 + random.nextInt(10), 5 + random.nextInt(10));
				g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + (h - 4) / 2 - 10);
			}

			g2.dispose();
			ImageIO.write(image, "jpg", os);
		} else if (type.contains("GIF") || type.contains("mixGIF")) {
			GifEncoder gifEncoder = new GifEncoder(); // gif编码类，这个利用了洋人写的编码类
			// 生成字符
			gifEncoder.start(os);
			gifEncoder.setQuality(180);
			gifEncoder.setDelay(150);
			gifEncoder.setRepeat(0);

			AlphaComposite ac3;
			for (int i = 0; i < verifySize; i++) {
				g2.setColor(getRandColor(100, 160));
				g2.setFont(getRandomFont(h, type));
				for (int j = 0; j < verifySize; j++) {
					AffineTransform affine = new AffineTransform();
					affine.setToRotation(Math.PI / 4 * rd * (rb ? 1 : -1), (w / verifySize) * i + (h - 4) / 2, h / 2);
					g2.setTransform(affine);
					g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + (h - 4) / 2 - 10);

					ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getAlpha(j, i, verifySize));
					g2.setComposite(ac3);
					g2.drawOval(random.nextInt(w), random.nextInt(h), 5 + random.nextInt(10), 5 + random.nextInt(10));
					gifEncoder.addFrame(image);
					image.flush();
				}
			}
			gifEncoder.finish();
			g2.dispose();
		} else {
			for (int i = 0; i < verifySize; i++) {
				g2.setColor(getRandColor(100, 160));
				g2.setFont(getRandomFont(h, type));

				AffineTransform affine = new AffineTransform();
				affine.setToRotation(Math.PI / 4 * rd * (rb ? 1 : -1), (w / verifySize) * i + (h - 4) / 2, h / 2);
				g2.setTransform(affine);
				g2.drawOval(random.nextInt(w), random.nextInt(h), 5 + random.nextInt(10), 5 + random.nextInt(10));
				g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + (h - 4) / 2 - 10);
			}

			g2.dispose();
			ImageIO.write(image, "jpg", os);
		}
	}

	/**
	 * 获取随机颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	private static Color getRandColor(int fc, int bc) {
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private static int getRandomIntColor() {
		int[] rgb = getRandomRgb();
		int color = 0;
		for (int c : rgb) {
			color = color << 8;
			color = color | c;
		}
		return color;
	}

	private static int[] getRandomRgb() {
		int[] rgb = new int[3];
		for (int i = 0; i < 3; i++) {
			rgb[i] = random.nextInt(255);
		}
		return rgb;
	}

	/**
	 * 随机字体、随机风格、随机大小
	 * 
	 * @param h
	 *            验证码图片高
	 * @return
	 */
	private static Font getRandomFont(int h, String type) {
		// 字体
		String name = fontName[random.nextInt(fontName.length)];
		// 字体样式
		int style = fontStyle[random.nextInt(fontStyle.length)];
		// 字体大小
		int size = getRandomFontSize(h);

		if (type.equals("login")) {
			return new Font(name, style, size);
		} else if (type.equals("coupons")) {
			return new Font(name, style, size);
		} else if (type.contains("3D")) {
			return new ImgFontByte().getFont(size, style);
		} else if (type.contains("mix")) {
			int flag = random.nextInt(10);
			if (flag > 4) {
				return new Font(name, style, size);
			} else {
				return new ImgFontByte().getFont(size, style);
			}
		} else {
			return new Font(name, style, size);
		}
	}

	/**
	 * 干扰线按范围获取随机数
	 * 
	 * @return
	 */
	private static int getRandomDrawLine() {
		int min = 20;
		int max = 155;
		Random random = new Random();
		return random.nextInt(max) % (max - min + 1) + min;
	}

	/**
	 * 噪点数率按范围获取随机数
	 * 
	 * @return
	 */
	private static float getRandomDrawPoint() {
		float min = 0.05f;
		float max = 0.1f;
		return min + ((max - min) * new Random().nextFloat());
	}

	/**
	 * 获取字体大小按范围随机
	 * 
	 * @param h
	 *            验证码图片高
	 * @return
	 */
	private static int getRandomFontSize(int h) {
		int min = h - 8;
		// int max = 46;
		Random random = new Random();
		return random.nextInt(11) + min;
	}

	/**
	 * 字符和干扰线扭曲
	 * 
	 * @param g
	 *            绘制图形的java工具类
	 * @param w1
	 *            验证码图片宽
	 * @param h1
	 *            验证码图片高
	 * @param color
	 *            颜色
	 */
	private static void shear(Graphics g, int w1, int h1, Color color) {
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	/**
	 * x轴扭曲
	 * 
	 * @param g
	 *            绘制图形的java工具类
	 * @param w1
	 *            验证码图片宽
	 * @param h1
	 *            验证码图片高
	 * @param color
	 *            颜色
	 */
	private static void shearX(Graphics g, int w1, int h1, Color color) {
		int period = random.nextInt(2);

		boolean borderGap = true;
		int frames = 1;
		int phase = random.nextInt(2);

		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}
	}

	/**
	 * y轴扭曲
	 * 
	 * @param g
	 *            绘制图形的java工具类
	 * @param w1
	 *            验证码图片宽
	 * @param h1
	 *            验证码图片高
	 * @param color
	 *            颜色
	 */
	private static void shearY(Graphics g, int w1, int h1, Color color) {
		int period = random.nextInt(40) + 10; // 50;

		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}
		}
	}

	/**
	 * 获取透明度,从0到1,自动计算步长
	 * 
	 * @param i
	 * @param j
	 * @return float 透明度
	 */
	private static float getAlpha(int i, int j, int verifySize) {
		int num = i + j;
		float r = (float) 1 / verifySize, s = (verifySize + 1) * r;
		return num > verifySize ? (num * r - s) : num * r;
	}

	/**
	 * 生成指定验证码图像文件 - 本地测试生成图片查看效果
	 * 
	 * @param w 验证码图片宽
	 * @param h 验证码图片高
	 * @param outputFile 文件流
	 * @param code 随机验证码
	 * @throws IOException
	 */
	public static void outputImage(int w, int h, File outputFile, String code) throws IOException {
		if (outputFile == null) {
			return;
		}
		File dir = outputFile.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			outputFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(outputFile);
			// outputImage(w, h, fos, code, "login"); //测试登录，噪点和干扰线为0.05f和20
			// outputImage(w, h, fos, code, "coupons"); //测试领券，噪点和干扰线为范围随机值0.05f ~ 0.1f和20 ~ 155
			// outputImage(w, h, fos, code, "3D"); //测试领券，噪点和干扰线为范围随机值0.05f ~ 0.1f和20 ~ 155
			// outputImage(w, h, fos, code, "GIF"); //测试领券，噪点和干扰线为范围随机值0.05f ~ 0.1f和20 ~ 155
			// outputImage(w, h, fos, code, "GIF3D"); //测试领券，噪点和干扰线为范围随机值0.05f ~ 0.1f和20 ~ 155
			// outputImage(w, h, fos, code, "mix2"); //测试领券，噪点和干扰线为范围随机值0.05f ~ 0.1f和20 ~ 155
			outputImage(w, h, fos, code, "mixGIF"); // 测试领券，噪点和干扰线为范围随机值0.05f ~ 0.1f和20 ~ 155
			fos.close();
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 本地测试类，可以生成样例验证码图片供观看效果
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		File dir = new File("E:/logtest/verifies8");
		int w = 120, h = 48;
		for (int i = 0; i < 150; i++) {
			String verifyCode = generateVerifyCode(4);
			File file = new File(dir, verifyCode + ".gif");
			outputImage(w, h, file, verifyCode);
		}
	}
	
	/**
	 * 3D中空字体自定义属性类
	 * 
	 * @author cgtu
	 * @date 2017年5月15日 下午3:27:52
	 */
	static class ImgFontByte {
		public Font getFont(int fontSize, int fontStype) {
			try {
				Font font = baseFont;
				if (baseFont == null) {
					font = Font.createFont(Font.TRUETYPE_FONT,
							new ByteArrayInputStream(imgFontByte.hex2byte(imgFontByte.getFontByteStr())));
				}
				return font.deriveFont(fontStype, fontSize);
			} catch (Exception e) {
				return new Font("Arial", fontStype, fontSize);
			}
		}

		private byte[] hex2byte(String str) {
			if (str == null)
				return null;
			str = str.trim();
			int len = str.length();
			if (len == 0 || len % 2 == 1)
				return null;

			byte[] b = new byte[len / 2];
			try {
				for (int i = 0; i < str.length(); i += 2) {
					b[i / 2] = (byte) Integer.decode("0x" + str.substring(i, i + 2)).intValue();
				}
				return b;
			} catch (Exception e) {
				return null;
			}
		}

	    /**
	     * ttf字体文件的十六进制字符串
	     * @return 字体文件的十六进制字符串
	     */
	    private String getFontByteStr() {
	        StringBuffer sb = new StringBuffer();
	        sb.append(
                "0001000000100040000400c04f532f327d8175d4000087740000005650434c5461e3d9fb000087c")
                .append("c00000036636d61709cbc69ab00007a64000005e863767420bb32bf1600000f2400000032667067")
                .append("6d8333c24f00000f1000000014676c7966302665d800000fc40000663c68646d7806beef5300008")
                .append("06c0000070868656164c6469a91000088040000003668686561068002f40000883c00000024686d")
                .append("7478e8bd09b4000077b0000001ac6b65726efffe00650000804c0000001e6c6f6361001a3196000")
                .append("07600000001b06d617870013e049f00008860000000206e616d65a927f7000000010c00000e0470")
                .append("6f737469df66ea0000795c0000010670726570eacfd8a800000f580000006b0000001f017a00000")
                .append("0000000000001de00000000000000000001001c00520000000000000002000e01de000000000000")
                .append("0003003201ec0000000000000004001c005200000000000000050024021e0000000000000006001")
                .append("a02420000000000000007005c0052000100000000000000ef025c0001000000000001000e028500")
                .append("010000000000020007034b0001000000000003001903520001000000000004000e0285000100000")
                .append("00000050012036b0001000000000006000d037d0001000000000007002e02850003000104090000")
                .append("01de00000003000104090001001c00520003000104090002000e01de0003000104090003003201e")
                .append("c0003000104090004001c005200030001040900050024021e0003000104090006001a0242000300")
                .append("0104090007005c00520003000104090008002c038a000300010409000900180aa20003000104090")
                .append("00a01300b16000300010409000b004c0a12000300010409000c00440c46000300010409000d0760")
                .append("03b6000300010409000e00600a120068007400740070003a002f002f006d0065006d00620065007")
                .append("20073002e0061006f006c002e0063006f006d002f00760072006f006f006d0066006f006e006400")
                .append("65002f007400740066002f0020002d00200041006300740069006f006e0020004a00610063006b0")
                .append("073006f006e00200043006f00700079007200690067006800740020002800430029002000310039")
                .append("0039003800200054006f006d0020004d00750072007000680079002000370020002d00200046007")
                .append("200650065002100200042007500740020006e006f007400200074006f0020006200650020007200")
                .append("650073006f006c006400200028006f006e00200043004400200066006f007200200069006e00730")
                .append("0740061006e00630065002100290020002d00200049006d0069006700680074006200650054004d")
                .append("00400061006f006c002e0063006f006d0020002d00200033003300390020005300740069006c006")
                .append("c002000480069006c006c002000520064002e0020002d002000480061006d00640065006e002000")
                .append("430054002000300036003500310038002e00310038003300300020002d00200045006c0069006d0")
                .append("069007400610074006500730020004f0064006f00720073002e0020004e006f0074002000410020")
                .append("0043006f007600650072002d0055007000210052006500670075006c0061007200460072006f006")
                .append("7003a002000200041006300740069006f006e0020004a00610063006b0073006f006e0020003100")
                .append("2e003000460072006f0067003a002000200038002f00320033002f0039003800200031002e00300")
                .append("041006300740069006f006e004a00610063006b0073006f006e687474703a2f2f6d656d62657273")
                .append("2e616f6c2e636f6d2f76726f6f6d666f6e64652f7474662f202d20416374696f6e204a61636b736")
                .append("f6e20436f7079726967687420284329203139393820546f6d204d75727068792037202d20467265")
                .append("652120427574206e6f7420746f206265207265736f6c6420286f6e20434420666f7220696e73746")
                .append("16e63652129202d20496d696768746265544d40616f6c2e636f6d202d20333339205374696c6c20")
                .append("48696c6c2052642e202d2048616d64656e2043542030363531382e31383330202d20456c696d697")
                .append("461746573204f646f72732e204e6f74204120436f7665722d557021526567756c617246726f673a")
                .append("2020416374696f6e204a61636b736f6e20312e3046726f673a2020382f32332f393820312e30416")
                .append("374696f6e4a61636b736f6e005b0044006900760069006400650020004200790020005a00650072")
                .append("006f005d00200046006f006e0074007300480065007200650020006900730020007400680065002")
                .append("000730075006d006d0061007200790020006f006600200074006800650020006c00690063006500")
                .append("6e0073006500200066006f00720020007400680069007300200066006f006e0074002c002000770")
                .append("0680069006300680020006d006100790020006200650020006f0076006500720072006900640064")
                .append("0065006e00200062007900200028006d006f007300740020006c0069006b0065006c00790020007")
                .append("6006500720079002000730069006d0069006c0061007200290020006e006500770020006c006900")
                .append("630065006e0073006500730020006100740020007400680065002000550052004c0020006200650")
                .append("06c006f0077002e000d000a000d000a004e004f0020004d004f004e004500590020006d00750073")
                .append("007400200065007600650072002000650078006300680061006e00670065002000680061006e006")
                .append("4007300200066006f00720020007400680069007300200066006f006e0074002000660069006c00")
                .append("65002c00200077006900740068006f007500740020004500580050004c004900430049005400200")
                .append("05700520049005400540045004e0020005000450052004d0049005300530049004f004e00200066")
                .append("0072006f006d0020007400680065002000640065007300690067006e00650072002e000d000a000")
                .append("d000a00540068006900730020006d00650061006e007300200079006f00750020004d0041005900")
                .append("20004e004f0054002000530045004c004c0020005400480049005300200046004f004e005400200")
                .append("06f006e0020006100200066006f006e0074002d0063006f006c006c0065006300740069006f006e")
                .append("002000430044002c0020006e006f0072002000730069006e00670075006c00610072006c0079002")
                .append("0006e006f0072002000700061007200740020006f006600200061006e00790020006f0074006800")
                .append("650072002000740079007000650020007000610063006b006100670065002e000d000a000d000a0")
                .append("059006f00750020006d006100790020006400690073007400720069006200750074006500200074")
                .append("00680069007300200066006f006e0074002000660069006c006500200074006f00200061006e007")
                .append("9006f006e006500200079006f0075002000770061006e0074002c0020006100730020006c006f00")
                .append("6e006700200061007300200079006f007500200064006f0020006e006f00740020006d006f00640")
                .append("0690066007900200069007400200061006e006400200064006f0020006e006f0074002000630068")
                .append("006100720067006500200061006e00790020006d006f006e006500790020006f007200200073006")
                .append("5007200760069006300650073002e000d000a000d000a0059006f0075002000630061006e002000")
                .append("75007300650020007400680069007300200066006f006e007400200069006e0020006e006f006e0")
                .append("063006f006d006d00650072006300690061006c0020006100700070006c00690063006100740069")
                .append("006f006e007300200061006e0064002000770065006200730069007400650073002000660072006")
                .append("50065006c007900200061006e006400200077006900740068006f00750074002000740068006500")
                .append("2000640065007300690067006e00650072002700730020007000650072006d00690073007300690")
                .append("06f006e002e000d000a000d000a0059006f0075002000630061006e002000750073006500200074")
                .append("00680069007300200066006f006e007400200074006f00200063007200650061007400650020006")
                .append("3006f006d006d00650072006300690061006c002000700072006f00640075006300740073002000")
                .append("6f00720020007700650062002000730069007400650073002c00200062007500740020007700680")
                .append("065006e00200061007000700072006f00700072006900610074006500200049002700640020006c")
                .append("006f0076006500200066006f007200200079006f007500200074006f002000730065006e0064002")
                .append("0006d00650020006100200063006f006d0070006c0069006d0065006e0074006100720079002000")
                .append("63006f007000790020006f006600200074006800650020006900740065006d00200079006f00750")
                .append("02000750073006500200069007400200069006e002e000d000a000d000a0046006f007200200074")
                .append("00680065002000660075006c006c0020006c006900630065006e0073006500200061006e0064002")
                .append("00075007000640061007400650073003a000d000a000d000a0068007400740070003a002f002f00")
                .append("6d0065006d0062006500720073002e0061006f006c002e0063006f006d002f00760072006f006f0")
                .append("06d0066006f006e00640065002f007400740066002f006c006500670061006c002e00680074006d")
                .append("006c000d000a000d000a004d00610069006c0069006e00670020006100640064007200650073007")
                .append("3003a000d000a000d000a0054006f006d0020004d0075007200700068007900200037000d000a00")
                .append("33003300390020005300740069006c006c002000480069006c006c002000520064000d000a00480")
                .append("061006d00640065006e002000430054002000300036003500310038002e0031003800330030000d")
                .append("000a005500530041000d000a004300720065006100740065006400200062007900200054006f006")
                .append("d0020004d0075007200700068007900200037002e000d000a000d000a0054006800690073002000")
                .append("69007300200061006e00200075006e006500760065006e00200073006f007200740020006f00660")
                .append("0200033004400200061006300740069006f006e00200066006f006e0074002e0020004c006f006f")
                .append("006b002000610074002000690074002e000d000a000d000a005b004400690076006900640065002")
                .append("0004200790020005a00650072006f005d00200066006f006e00740073003a000d000a0068007400")
                .append("740070003a002f002f006d0065006d0062006500720073002e0061006f006c002e0063006f006d0")
                .append("02f00760072006f006f006d0066006f006e00640065002f007400740066002f000d000a00680074")
                .append("00740070003a002f002f006d0065006d0062006500720073002e0061006f006c002e0063006f006")
                .append("d002f0069006d0069006700680074006200650074006d002f4001002c764520b003254523616818")
                .append("236860442d000b030900210018001b0023001c009b003d0063012d0169011001d800b801b200de0")
                .append("0f901d55a725a725a725a72000400060000401f1212111110100f0f0e0e0d0d0c0c0b0b0a0a0909")
                .append("08080707060601010000018db801ff8545684445684445684445684445684445684445684445684")
                .append("4456844456844456844456844456844456844456844b3030246002bb3050446002bb10202456844")
                .append("b10404456844000002003f000001b60320000300070056402001080840090207040201000605020")
                .append("30205040500070605010201030000010046762f3718003f3c2f3c10fd3c10fd3c012f3cfd3c2f3c")
                .append("fd3c003130014968b900000008496861b0405258381137b90008ffc0385933112111253311233f0")
                .append("177fec7fafa0320fce03f02a300060024001000fd03060028003500480055005e00690091404401")
                .append("6a6a406b295d5a3d3a5c56095303175f3b3a021b0029022f65032f3603034642403f3d054503040")
                .append("34a4902070b05495549050d68052c4f04233204612c002301011746762f3718003f3f2ffd10fd10")
                .append("fd2ffd3c10fd012ffd3c2f3cfd173c10fd2ffd10fd3c2ffd3c3c2ffd2e2e2e002e2e2e2e3130014")
                .append("968b90017006a496861b0405258381137b9006affc038591314061d011406071607060706232227")
                .append("2627262726272635143736353437363736373633321716171613140623222635343633321716033")
                .append("4272627111633143d01343534263d01343603113426272627060706151413173627263516071736")
                .append("2734232207061514163332fb0705010401050c34330d02180a0103050f0c01010d072d0e2620030")
                .append("412190d0102361a1e3a30222818161c020310090802062b04010103234a03258a01070a03090a0c")
                .append("202218131526122a02a203640289012a0a3e66060705010f15041e43958401072a2729180603030")
                .append("103020a0d2802fd961e2d392022301f1b021d19070e1afe000506254412291440015e0366fe5101")
                .append("db031d04010204041f373cfe988e15070a01320c07121a390e1119122400ffff001001aa026b035")
                .append("50027000ffffd024f0007000f0140024c0006002e0045021f02a7002500e500e900ef00f9010201")
                .append("30408101fdf1f0eae9e7e6e3d3d0cbc9c3bfb8b4afacaaa89b997c6e6a5d5b57504d3a3930241d1")
                .append("8110efffaf0eceae8e6d5cecbc6c4bdb9b1afa593898682807674717066655f322a26221b1a0908")
                .append("07001f030514020305f502903a029059029035022d530290dc053e0b05552a2804ee553e04f6f54")
                .append("704d74a4404ded77a9e7e019346762f3718002f2f2fb901000004fdb801013c2f3cfd3c10fd2f3c")
                .append("fd2f3cfd3c10fd10fd012ffd2ffd10fd10fdb801003c10fd2f3cfd10fd2e2e2e2e2e2e2e2e2e2e2")
                .append("e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e2e2e")
                .append("2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e313001b9010400264968b90")
                .append("0930103496861b0405258b90103004038b801031137b90103ffc038590114060736151407171514")
                .append("23222623220623222635343736333217071617363534263534371637142322271e0115140623223")
                .append("5343635342e01272314070623222726272623220623222623220623222623220615141736333215")
                .append("14232235061514171617161d0116171617363736333217150622071615140706232227061726353")
                .append("437262726273e0135342627262726372e01353437363736373233323633321716171e0115140623")
                .append("2227062322262316151406232227262707161716151423222726270716151406232227141615142")
                .append("3222623220716333236373633321736373e013332162f01071f0106071633322723060706173336")
                .append("3736132e012322071533320213100101140706173f030d370e040b39261f05051315290f041c030")
                .append("c440a0b010f340a0d2b1302062c16102a270a0104010105110405100404090302260b040a180a08")
                .append("0f12030210030d090b5235290d1a12120827062413091813170f0b0d0713061a4b3f22031c0a071")
                .append("3033105081026231a0f0a03190a2a0a2d3f471e090f0b04050b060808110f150b040218031a0a08")
                .append("090d1407150d0e0e200b040229050e06111d431c5925030d02111c331014270618112e1b5607040")
                .append("82c062b100409980d0216100110020e144c020c04040f0c060127021102031b140f040802260b0a")
                .append("050e080501130b1716070410050d14029830010724030a2d10091d040322092d250c091d0d17010")
                .append("4040d190e040d0a0e0f0f02050a433a0a150e180303110c0f030422110f04141101140a08121313")
                .append("0c130a0909100d111c02110f0d071480192708100d1a3732150d02070e0f1406260d050a0b0b250")
                .append("e0f040b17030d0208090d030f17101002120b050a1b030e03152543160e010a190106021613620f")
                .append("010e630434052b05110d0f0b0f17fea80109071200ffff002e0045021f02a7000600060000ffff0")
                .append("02e0045021f02a7000600060000ffff002e0045021f02a7000600060000ffff0013019c012b0344")
                .append("0007000f0000023e00020018ffdc010f030200220030004a40190131314032002923292318001e0")
                .append("2272d020c0b031401010b46762f3718003f2f012f3cfd2ffd2e2e2e2e002e2e3130014968b9000b")
                .append("0031496861b0405258381137b90031ffc0385925140623222726272627263d01143736373637363")
                .append("71617161514070607061514171e010726272635343706070615141716010f290c04253a1e141914")
                .append("070c1e283c08100d0c130e1302112f02215b29101944371f1b171e20063e1926382455442f60053")
                .append("a5a49631a060809090d061b384a0a6059a3780638037e42695e899c2f695858754c6200020013ff")
                .append("f900f303150018002600494019012727402800211d211d120a1902001f030c14010800010a46762")
                .append("f3718003f3f012ffd2ffd2e2e2e2e002e2e3130014968b9000a0027496861b0405258381137b900")
                .append("27ffc0385913140706070607062322273635342726272635343716171e010734272627161514073")
                .append("637363736f30809072956241010054a1a0212100b05184666411d2035363c101a22171501ad1c51")
                .append("55147f431c10b981ae970d3a36050605011236c8535156612da1f483a10d1c35746500000100290")
                .append("23000e102e4002f006140260130304031002b201f1d23201704000e271b0e1f1402061f022a272d")
                .append("05090d05252511011b46762f3718002f2f10fd2ffd012f3cfd2ffd10fd10fd2e2e2e2e002e2e2e2")
                .append("e3130014968b9001b0030496861b0405258381137b90030ffc03859131407060716151406232227")
                .append("262706070623222635343637262726353433321733342635343332151406151736333216e1120d0")
                .append("d260d0a09130d0c08080c08081614031312161110250606101f02041b08051902920a0402012505")
                .append("0b17140e0e0f0f1717080a140b030205131f1308210812180413050613250005001800dc0210030")
                .append("c00300038005e00690072009b404401737340740070665a5150494740351f076e6a625c5b4b4745")
                .append("443e3b39312b291817090700555402354e03695f6261051c57050c383105046a040c54046c0c230")
                .append("1011746762f3718003f2f2ffd10fd2ffd3c10fd2ffd3c012f3cfd2ffd3c2e2e2e2e2e2e2e2e2e2e")
                .append("2e2e2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e2e2e2e3130014968b900170073496861b04052583")
                .append("81137b90073ffc03859011407062322262316070e012322272627263506272e013d013437363332")
                .append("1633363736333216171e01151407161716171607262726271e01333726353436352627060706271")
                .append("70607363332151406151417333236333534333216333735343605262b0116171633373635172623")
                .append("22071617323602100c0f0d1349130101042b3e380a2505021d36072c1809111042110207050d0a8")
                .append("9030c250d272810082741032c232218212e09901129520408068b0403010e3943050412103f1019")
                .append("1552150206fed71f341716040c1c2602b0191e0453070e145101e21a3943134b081d13063419085")
                .append("00208033e07512c050105535c0a1a0309370c1d3c0809040e4477200906062510af1c1219611806")
                .append("0d525a0a04040a4d022d124412100907641f1501150f3d4d081d030801060eb925070b160300000")
                .append("30013ff5e012b0106001d003600460067402901474740481a37313d312f25230a042928020f1e02")
                .append("383733023b43021a3f0500232205131300010f46762f3718002f2f10fd3c10fd012ffd2ffd2f3cf")
                .append("d2ffd3c2e2e2e2e2e2e2e002e2e3130014968b9000f0047496861b0405258381137b90047ffc038")
                .append("59172227302736373637363726272e01353437363332171e01171615140706132627262b0116151")
                .append("4061d0116171617161514073635342726371514161514071633323736353427268511171e010b08")
                .append("08120c14220d230c061d7724054102062d3103070509661f02050e161b08232e6a04041f0986120")
                .append("23a27230601a21f2d080d0a09191d010a0a481176100a170374071c324a3b4001244a0b0f040604")
                .append("1004580602040209312e40295b0e161b10070c2f0c73351536323c2413020003003c0109021d01e")
                .append("100200038004f007a403401505040511f48443f3e4e432c3821021f2d0210323105072e2d050c04")
                .append("054b2505183836053b4b040722212a0418141b0c011046762f3718002f2f3c2ffd3c3c2ffd2ffd3")
                .append("c10fd10fd10fd3c10fd3c012ffd2ffd3c2e2e2e002e2e2e2e3130014968b900100050496861b040")
                .append("5258381137b90050ffc038590023220623222623220722062322272635343736173217163332363")
                .append("33217161514272322062322262326232207173332363b0132373637361f01262322062b01220706")
                .append("23173637363332163332363326020e2e0d350d1144112b590513051631070b21942c44410c071e0")
                .append("71c100c440e081e080b26092d2c6649062a030c037e1120250e104a041123185e1682070c0f041a")
                .append("08123052114512103d0f060112030405054952210b040d010201041e171d67970808020a5204050")
                .append("601010123030c02021c03030404040b0000030012000500f200c50012001a00270051401e012828")
                .append("4029001715171b020013030021020a2505051e040d0d0500010a46762f3718003f2f10fd10fd012")
                .append("ffd2ffd10fd2e002e2e3130014968b9000a0028496861b0405258381137b90028ffc03859371407")
                .append("0e012322262726353436333217161716073427160736373627342623220615141716333236f21e0")
                .append("43a06134d0b132f2323350e190f1a160c150c0b082e261b161d11131f151c5e321a030a2b0e171d")
                .append("23301f0716151d200c1e270303042517291c161a1519260003002b0051015d02dc001b002300320")
                .append("058401e0133334034002d25222b24201c1200292b06141212141e0419190f011246762f3718002f")
                .append("2f10fd01872e0ec40efc0ec4012e2e2e2e2e2e002e2e2e3130014968b900120033496861b040525")
                .append("8381137b90033ffc03859011407060706070607060716070e0123222635341336373e0133321627")
                .append("26233207163332072706070607060716173637363736015d0c0c011b1b1e0e0c0d010503350e104")
                .append("4551e39052a0c103b1d1410031923030a1e1f1a1e0e181d3c0f1f2c1c072c1f02a901181b034848")
                .append("553e337014270a16230e15011f68910c212503130d150a0f3a5e214366ca070fc35616765600000")
                .append("600060012023c0303001b0025003d004d005c006700794035016868406900645e241e66645d231e")
                .append("5702484f4e023e1c030034020c2602206202483a050853054c2e041259044412010800010c46762")
                .append("f3718003f3f2ffd10fd2ffd10fd012ffd2ffd2ffd2ffd2ffd3c10fd2e2e2e2e2e002e2e2e2e3130")
                .append("014968b9000c0068496861b0405258381137b90068ffc0385901140706070607062322272635343")
                .append("7363736333217161716171617160734271615140615173627342726272627262322070607061514")
                .append("1716171633323736271407060706232227263534373633320335342726232207061514333237362")
                .append("f01060706151417263534023c0508010e3a3e54a05b5328336b250279450d192f1113070b1a3922")
                .append("0818073e050701073c3f51ad1d010b09192623344f833324470d1114193850261c1b2341962a120")
                .append("e0836241b5425141093101e08031802012a0f162005553a3f746aa57f59701d093c0f1d36222743")
                .append("624ce54e8969124713022b5d1b29380c534044d70d221d1278395926376447b731556513196e4e6")
                .append("34b3f52fea3a70f490b6a5246893025f0020c1f0d33452e18165f00000400290006020f030c0045")
                .append("0059008a0093009340420194944095008e716d645e5c59513e211f917e6b4f4e47463c170f260e1")
                .append("a00025a79031a75031d4b03385a038e8f6003586203544005049183050b2f010b00010f46762f37")
                .append("18003f3f10fd3c2ffd012ffd2ffd2f3cfd2ffd2ffd2ffd10fd10fd2e2e2e2e2e2e2e2e2e2e002e2")
                .append("e2e2e2e2e2e2e2e2e2e3130014968b9000f0094496861b0405258381137b90094ffc03859251407")
                .append("0623220607060706072227263534373637363336353426353436353427060726272e01353437363")
                .append("736373e01333217161716151416151407061716173633321716171627351427263534263d013427")
                .append("1406151417161f02262706232227023726232206070607060716173637363330171615140706071")
                .append("60f010607161732163332373637363736373426270714173426020f140a24081e05204033830842")
                .append("190b043c380e04030903040d12240f3a252c02080e08920f0e18150408020202010106310511082")
                .append("40903970202071802050504115d04042b3d17020c011209115e0a1515191712261011180b0a0c04")
                .append("05010203086321070e030d0421334b092e2d364e10020117044a1c080407010206070549431f0e0")
                .append("c03070703110b2b0319651a11110808081308490e073b46031114020d252211275d196f19102620")
                .append("131c1905061e2e13600d09211c59208b1f5903220e3d0f4f745e5f0a7a22420735011bc7040c052")
                .append("4252b1a182e101118020a821a2a370d2546060c011e3b020609010203043c080f034b10140e3b00")
                .append("0500210022024702f60040004a00870092009b00ae4054019c9c409d009b918c6c49209377694b4")
                .append("6368802715802001a027199030f8e032441037160032f850205030744056e1c056e830509807e7c")
                .append("037b050c3d054b4b043a38363a0497545250044e63042c2c09012446762f3718002f2f10fd2f173")
                .append("cfd3c3c10fd10fd2ffd173c10fd2ffd10fd012f3c3cfd2ffd2ffd2ffd2ffd10fd2ffd10fd2e2e2e")
                .append("2e2e2e002e2e2e2e2e2e3130014968b90024009c496861b0405258381137b9009cffc0385925140")
                .append("61514151615142322242322263534373637363736373e013534232207060734272635343e013736")
                .append("3736333216151406070607060732331633323633321716033426232207321607361322062322232")
                .append("6232223262322353437363736373635342623220706070615161f01363332161514070607060714")
                .append("1716173332173233321633323f01360134272627061514161736132627262706353017024708012")
                .append("d46feec4509321c072d1329233c05102a20343b17282008060c27544a44596c2f2612232e0a111f")
                .append("220e1042110d151be418090611111a0310c51556150b16160b0a12120a19394006291c165d4e373")
                .append("c19313a0f210b7f29202d841b251e1e080c020c1527271520802026230501fe471110070a22060a")
                .append("120305060b1525fb0c320b070e0d07670487121f22082a162a17370628081414160401372d05012")
                .append("2210d2c1b18725a296233171b250a0108060801120911121c1016fee90501010f072f35062f4837")
                .append("0f4f620f06181b0e1c38043a2f203e6214251f1e1c2c3b0c0106014615015906201d09200604360")
                .append("814fe460912264b2f016e00040010000501ed0313003f0048007b00850099404601868640873c80")
                .append("7c6f5b5a4340277e716458574d423a2d1d0951023678023c13026223026a6247023684023c66670")
                .append("56d0d050073050025055e10056d55042f2f010000012d46762f3718003f3f10fd2ffd2ffd10fd10")
                .append("fd10fd3c012ffd2ffd2f3cfd10fd10fd10fd2e2e2e2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e313")
                .append("0014968b9002d0086496861b0405258381137b90086ffc03859372227262726272e012736373633")
                .append("3216333236353427262726272627263534373637363534232207262726272627363332161716171")
                .append("6151407060716151407061306071736373635340726272635343736353427262322071534173332")
                .append("363332171615140716173736161514062332270607163332373e013534272617060716333237363")
                .append("534b02208130e060f09110111101d0d0b2c0b1321341608030b010604131c1c262a1e4e0b1a1904")
                .append("050f56693276221612151917236a6b527a045412191e1c65111015125d4337435b420d041040103")
                .append("2222e76050a1320343b2501400f1d2e1c5e37284c191c5306370414110d0b0501051208150c190d")
                .append("2324380e110f1f032b1c0d3e05130e0a07090c0b141218200325220d12592b2b21151e23192b302")
                .append("c1f578375362802796d4905013d371f11bf0102040f0212584e3c231c1a0802440f11152f502424")
                .append("4901012f21243a0c1d3d141811702b2a2a2fa2234e192922161200000500160013022a030b00400")
                .append("049007b008a009400974043019595409600908b857c7a715f53514841342e03908b857c6f635b4a")
                .append("462d2b18150057023d69021c4102394d4c033d5003390d040f2a04267605052601100f00011c467")
                .append("62f3718003f3c3f2ffd10fd10fd012ffd2ffd3c10fd2ffd10fd2e2e2e2e2e2e2e2e2e2e2e2e2e2e")
                .append("002e2e2e2e2e2e2e2e2e2e2e2e2e2e3130014968b9001c0095496861b0405258381137b90095ffc")
                .append("03859011406072623220706070607060706072306272e01373e0137262726353437363736373637")
                .append("36373217161f010607173637363736313216171615140706071617162706070e011516173617262")
                .append("7353436372706232226223534373637262726230607060716171617161514070607060716333637")
                .append("3e01333217163336010e010706310607060736373637361306070e01073e013736022a300c0a300")
                .append("506060c0202042007101418383f1401043203383756121b0209090f0b172f111a151603101e4007")
                .append("1603211e0a6803060e1202171722bf0b100513070a0eb818311c015c27100156321216040518110")
                .append("b0d1b1120205149260c0d0f0209205a19051f0307100e161f040bfe8f130d03050a120d14080c07")
                .append("23219a1720092e010e1e081801b61c6e02042f2a441d1d1916070d01090a120f25d2150b0c15192")
                .append("4334d0828273d0811220806060c41820c2840060e0d1103060a1f2f3e0e0b0a11c1030c084c0a02")
                .append("0627830b19051e6d190c9c1e0f23424f17010504305f31620a0e0c0f050c072c3306407f0c35c61")
                .append("02305081c014b0f1814201c372c4c040a087c75feb50b160dd917050b0a7100050018000d025103")
                .append("00003b0049007c0088009800974044019999409a00908b8783817b5f4f443c291f0490898174615")
                .append("34a3c1b08002c02574424025769020e7d035778050606044795041485042622045b65048e320114")
                .append("00011b46762f3718003f3f2ffd2ffd2ffd10fd2ffd10fd012ffd2ffd10fd3c10fd2e2e2e2e2e2e2")
                .append("e2e2e2e2e002e2e2e2e2e2e2e2e2e2e2e2e2e3130014968b9001b0099496861b0405258381137b9")
                .append("0099ffc0385901140e0107262706071617161716151406070e0123222726272e013534373637321")
                .append("633323534232206232226353436373637131617161716171617160734272627262726271e013332")
                .append("163726272e012706070615161716151407062322272627060716171633323736353426352627262")
                .append("726272635343e01333216333603342726271617363332173617262322062322271e011716333237")
                .append("3602511f0e10438f070319313c272227301c6a2227464e160916111106077d233a600a24090d320")
                .append("801030954376c16a2161e0405054509072c1e1d264d0a16160f854236353e6163052a26652e5524")
                .append("2025222d2726100b51081c4364414a111a241c3a191a281d06092baf2c09da4d136501113c04541")
                .append("c035a06060368215d6f05164f461e23334002520e680d041e20171b050c0f463e433c4b2415230f")
                .append("1214083f0f07302d0b2c1d3407570f041903172f010f020a0328040d052c2778141b070704040c1")
                .append("8111f39c30f0f100a08377b6f46010f1a4c2217140c0c0c13302603082d34600f27082b120d0602")
                .append("020409088f0a3826feb03614050b05240a4a08a112292f1d1a0f0e0f13000007001800170214030")
                .append("e00260030004e005900680074007f00ad40510180804081257a75746f6e6c5a5440332d7e6f6e58")
                .append("54352f2d2c1e13023d4b022578025d46020a74693d026528026572050461054f282705105604041")
                .append("81a042f311e04204f044848045210010400010a46762f3718003f3f2ffd10fd2ffd3c2ffd3c10fd")
                .append("10fd3c10fd10fd012ffd10fd3c3c2ffd2ffd2ffd10fd2e2e2e2e2e2e2e2e2e2e002e2e2e2e2e2e2")
                .append("e2e2e2e2e3130014968b9000a0080496861b0405258381137b90080ffc038592506070623222726")
                .append("27263534373637363332161514070e0123262322070607363332171e01151403232207061d01363")
                .append("72607220726353437363736373635342f0106070607061514333236353427260322062322271633")
                .append("3237262722263534373633321716151407063734262322073132363332170722060716333237363")
                .append("53401ea1c36392c57494423142921585c691623080309070a02912106074f2d3a212b40910e1526")
                .append("293f3b063756651510151c4c6d0204071512b14121f04566191c48081d07453541440c36146f234")
                .append("33e33392a1e213a2d4322143015071d07341c522839051716292835812720233a36543155358267")
                .append("464932175a100703027113251d0a0c5c2e6d01cc111210031f010ed44c022314374e20590e21021")
                .append("0180307031ed2692ef76c46501e21fea7051129120b4e3d2334211b15182a4120188913171b0517")
                .append("042b25160d101d2c000005000c0012023102ec002c00390044006b00720093403c0173734074006")
                .append("f6c665b56554b49413a362f19156f6d6c645e533e3a36312f1f1511666808060404066668080706")
                .append("06074502004d0425250b00011f46762f3718003f2f10fd012ffd872e0ec40efc0ec4872e0ec40ef")
                .append("c0ec4012e2e2e2e2e2e2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e2e2e2e2e2e2e3130014968b900")
                .append("1f0073496861b0405258381137b90073ffc038590114070607060f0106070607262726272635343")
                .append("736372207060726272e023534373637363332171e02171627163736272635342627161716070607")
                .append("06071e013332373637342726272607062322070607060716073332373e013332161514070607060")
                .append("716173637363736013534271417160231201a1a3425540d1c0513054b340604524b19253a401920")
                .append("081d0509060abcae2e380e06220803041f0a020202030c0c020601d3314d720d05200509b806cc0")
                .append("706060d16190a23170d94613f0d01082fb006220707101f1c0929651b3d407f181826feed680125")
                .append("02561f37282958459f1c36080703251a0928120e9f922a0c0d021a061a0f7e0d0a05080f0d06032")
                .append("50d2925060a04030f1104171a08102610410a070b0206191f034a151e1a19010102020109060a48")
                .append("2e19010a0e06073e3a1045d1132871e52c2c45fdd0090f3c081112000a0003000c0214031600190")
                .append("021002c00500060006f007b0088009400a300002506232227263534372635343633321716171615")
                .append("1407161514060316151407363534031e01151407163534272607262726373e01353426232207061")
                .append("5141716151407060706151417163332373637363534272227263534373633321716151407060322")
                .append("27263534373633321716151406032206151416333237363534270e0115141735342635343726132")
                .append("20615141633323635342627220706151417263534373637342601a0454a2536b331257a690a4e61")
                .append("2a333b474828351d2e461b280414161a190a38020a20288a5a4d312e24070a0f011d3a3c6b39303")
                .append("81519ce362b2e1b20363b211f171b1d462b31161c2e772414422e20291a231e130f8f0e161f0533")
                .append("0848233122271e321d801b0f0b2509141218182a1e1239ca6f312b4969781c232d37616c2647623")
                .append("06a0275494c2941105071fef11c5e271d0d012f2b323a36103f0906154b2759813c384f3d2a0905")
                .append("09090d0227576b424516192e321f2c9c25263538272d29253c2e262efec61f2444322b345a301f2")
                .append("b4401ee3222241e231d24322c0d3d16211f010616063b2715fea83624271e331e292534271d233c")
                .append("1918191b201d100d160006000c000b01ed030a003300570076008c009600a400b6405501a5a540a")
                .append("600a0957f706f4436a3a09e97918d8577737269584a48403e3c2002001a036f1e036f6503273403")
                .append("0446030d4d030a07545250030444030d99052b8e8d058b72050f2120056761042b2b01100f00012")
                .append("746762f3718003f3c3f10fd2ffd3c10fd2ffd3c10fd012ffd2ffd3c3c2f3cfd10fd10fd2ffd2ffd")
                .append("10fd2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e3130014968b9002700a")
                .append("5496861b0405258381137b900a5ffc0385901141506151406151c01151406151407232227262726")
                .append("2726272635343736373635232227262726353437363332161716171617160730270615140706070")
                .append("617160714171617363534373435343635343635342726373637362f012627262332272623220706")
                .append("1514333215140706070607331617353437360714070615060706233027262726353437363736333")
                .append("20723220615141716173627262322070e011514173426353401ed0107060e3d0d2327060a0b0b0b")
                .append("0c0303010a1b1d353e2e2c18327d424b272910060b1c15181203040101030207080e0a020106070")
                .append("201010103033206081b160e0d40193a6d2d16e72203040103060b2346090d6205060101030c3029")
                .append("2a3111102f11432c1d2d1126213108014f0d0b07080419240302751b33321a115310174b170d3c0")
                .append("d771604050305100c0b0f0c0e161a09bf0a0e123f3b3a1e38720b0508140b16360d347f7f132026")
                .append("0d1c5d4b2d05080d0e411b0f2222101a591b0e460e0b271e140714116405020605090464321a8c1")
                .append("9121c25084b940505cc5483c52715202d0914141d0e0a0a1125172b270904432f13200406051765")
                .append("1305034108131003090337ffff000f000500f2022b00260011000000070011fffd0165ffff0013f")
                .append("f5e012b02540026000f0000000700110007018fffff0018ffdc010f03020006000b0000ffff003c")
                .append("00be021d027700270010000000960006001000b5ffff0013fff900f303150006000c00000007000")
                .append("1000501d90314003b00460052005e0083008d009900a9404e019a9a409b00968a8474695b554f49")
                .append("0c8e8a84807a7672534d4925221608046a69024b5b5d02705a5902313c02421c037062033142039")
                .append("34703001e056d290564675f043645049140003601013146762f3718003f3f2ffd10fd2f3cfd2ffd")
                .append("012ffd2ffd2ffd2ffd10fd10fd3c10fd3c2ffd3c2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e002e2e2e2")
                .append("e2e2e2e2e2e2e3130014968b90031009a496861b0405258381137b9009affc03859011407060714")
                .append("1716153007062322272627262726272635343736373635342322070607141615140706232227262")
                .append("72627263534373e0133321716171603140706232235343633321334271615140716173637362526")
                .append("232207061d011726353437220615143332363316173534363332161514071617363726272635343")
                .append("6373e01353427261326272627262716171607342623221514163332373601d9161924100a04421c")
                .append("0e141705080606041e1c23121d222d1b1513030509392606151601050474193c284c3a1616351e0")
                .append("f11196c241b66050e03610c131c1a17fef5050b0d0b0909015f5c751a0412040a13562f2b366905")
                .append("271a33021817120918392a2e470606070c060e0a091102170e271611100b0a022332383d1904412")
                .append("a1c0d1609080b0d252c0c593003161b172324301b152f0a280b1e0402020a30040e0a0b8d400e10")
                .append("2e112048fddf1a15184b1b230194242210106a55090b01493e790316120e4707080948ae7e5b1d0")
                .append("20402432e41342b534138aa0309084d4802090a040f681d5c3338fdeb1e1d23160407242437900c")
                .append("1227111a1310ffff002e0045021f02a700060006000000060000001502a8030d003f00620073008")
                .append("0008c00930094403f01949440950092908b8577655f5b4f4e410f928d87817b746e635340004a48")
                .append("4c0627252527130221110257564c02212f043370050a7d051b33011b00012146762f3718003f3f1")
                .append("0fd2ffd10fd012ffd2f3cfd10fd872e0ec40efc0ec40ec4012e2e2e2e2e2e2e2e2e2e2e002e2e2e")
                .append("2e2e2e2e2e2e2e2e2e3130014968b900210094496861b0405258381137b90094ffc038592514070")
                .append("6070607060706232226272627060716151407060706070623222726272635343736373613363736")
                .append("37363736373637363332161716171617161716171627030e0107060706070607061514173332373")
                .append("6373426313534373633321716173637361726232207060706070e01071633323736052e01232207")
                .append("06071633323736131407062322353437361716072e012706153602a8101a331c360619140413460")
                .append("10819182413070b2402094b160b30260219140d0d034b0d17070d191f16170e2620040a36040f1d")
                .append("161e23121b1c276ece1c841d12070d0a062f27040c163e3214053d370c15130e0d1740375619020")
                .append("5212a0c192b0a1403270b04404ffe9907140404341b2f2a04062c31600a470d1519201e0f200310")
                .append("030609de0e080814101a020d0a520d4555050d823820070d18050828211b021513274128280b013")
                .append("8352c04070d0502030309081b05263f2a48522536364d4001c40d150f372646271bb5920d07041f")
                .append("1a0e0e3c700e1b194f42420a13102f220f1205050b040909271f276205141a0d0c23161801bb130")
                .append("41e19382c11010f45061906210e03000009001e000502310309002d00510061007300830092009c")
                .append("00a800b100000114070607161716151423222726272635343736373637363736373637363736373")
                .append("6333217161716171617161716073427262726272623060706070607061516333236373e01353427")
                .append("2627263534373637360326272627262715141617161716333213140623222e01353436373633321")
                .append("71617160314062322263534373e013332171e0137342726270e010732163332373e012734270607")
                .append("32173637361134262b010615141716333227342627061514173602311e2131170501c2b031060c0")
                .append("b23151402030911010d0916020204090212276415161e1a071b1f072036131621437f0d16291c0a")
                .append("191b0802a35b0c0f03253f040d4007262f1827c80f2a33193f2d0b031942343d0d9643340c580e2")
                .append("a080b0f3151120801603b1b145b18020f07050635553c1c181c0518050515031411092772110d16")
                .append("0f0a040c0b1f120d1013100922510a01140f1001dc36383e0d1e22082aac510a322f070c8e53520")
                .append("d1820420818152907060b04011c06060a10052428145c042a343e12231801588d325e6525072170")
                .append("0601034b2a0a0f361e040b0713181a2afe5f0f0b0e0e241e0805240426120f01ff3637160c0b0d9")
                .append("90b10360c2905feb41f33380f0a730915050456e718110f021042100303021b52042b2d6403112c")
                .append("25fe890e2331030908066a041c044711050d4a00000500170067022902f7002b0035003c0049006")
                .append("d00804037016e6e406f004c463f3a36332f27594a463d3b3a36006202082c02131e024842025357")
                .append("02316a05022120054e5a59050f02110f01010846762f3718003f3c2f10fd3c2ffd3c10fd012ffd2")
                .append("ffd2ffd2ffd2ffd2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e3130014968b90008006e496861b040")
                .append("5258381137b9006effc0385925062322272627263534373e013736333233161514070e020706070")
                .append("e011514173332373637363332161716033426270607161734361326272627151403262322061514")
                .append("1716172635341326270623222627263534373637363723220706070607061514171617161716333")
                .append("2373602294759b98614110e0b11814661400d094804061015422c1c0e1a1f06292b0715120a0b47")
                .append("03064b0b0501060b07052e0203021c911a1b2831110a210cda060735771c4a08075940640207226")
                .append("638512a090f1a0f1010274a42430c2b2787208f16463e27392f477e080b2b12202e3f0506160d17")
                .append("0b2f11411d050105044d0c1c01d2060c06244706091141fe191919141b104201550e3f29241c112")
                .append("21b2b54fee12345124e1e1a1a552e220824471219450b163158213c42142e1b180d0c000006000b")
                .append("000d02410313001900330043005a0063006f007c40330170704071006b67605b564a3c206b604e3")
                .append("c34280a242606100e0e101a02006402445c5b02444204062a043812010600010a46762f3718003f")
                .append("3f2ffd10fd012ffd3c10fd2ffd872e0ec40efc0ec4012e2e2e2e2e2e2e002e2e2e2e2e2e2e2e313")
                .append("0014968b9000a0070496861b0405258381137b90070ffc038590106070607062726272635343736")
                .append("3736373633321716171617162734272627262306070607060706071633323736373637363736032")
                .append("6272627262726271417161716333213140706070623222726353437363736373633321617160735")
                .append("34272627161716173426270607060736373e01023e0624372e3f7a9b1c34231516111518185151b")
                .append("42109090932322c624a4b030c0d030a17163963662e270e3f36180c130cb71111102346203e2a11")
                .append("0b0d234b6d8809010638921d18224a070e0c010c0f0c71161723260d420d113e0d2e23121218131")
                .append("62a233d01615f4263212f0302335d06117f5050447681224e962828313067423b2d231520280d38")
                .append("6e61d7370f05201c321a5137fea30a0b0802040409170220140814017a08290d187e070b1506dc1")
                .append("c383301254d272a351235250e2c1f3d20732644113f3f522501090d410000030007000a0225031b")
                .append("00370068007d0072402e017e7e407f007d7165644946453b3a333126256958524e43382f231c003")
                .append("f02287303125554041818010600011246762f3718003f3f10fd3c012ffd2ffd2e2e2e2e2e2e2e2e")
                .append("2e2e002e2e2e2e2e2e2e2e2e2e2e2e2e3130014968b90012007e496861b0405258381137b9007ef")
                .append("fc038592514070e0104232227262726272627262726353436373624333217161514060706070607")
                .append("161737161706070e010706153017363332171e0127263505272e013534373637262723220623222")
                .append("7342635343736372635232204071617161714171617161716153332373605342726272627262706")
                .append("1514171615161716171617022506070afe9207180805091d150e0e020b091d0e1a013f1d130b081")
                .append("207214a531d05059b030e080409371b2a09a642100d04142908ff00090119422a2a040607137204")
                .append("0a02130804ee061905fee51b02060d16140206010f0a0727788efeaf0c100213131a050d080b130")
                .append("50d11090fba0d1c21085e090e163f9770710b3025161345070d4b32241e092e04140f120d221114")
                .append("244725080e09040609462d080361082d1432060e92010d0c070715291a05018a030904024225264")
                .append("c081b354b98025e152808291f11242c69172c360c6c6b90422109122b3a04801f55492447000006")
                .append("001b003402650302002f003a0044004b006d007b00854039017c7c407d00787064635e5b4a49473")
                .append("f3b3932756e68634c494542350a7202243003003c3b031051022457051e2b04505005281e280101")
                .append("2446762f3718003f2f10fd10fd10fd012ffd2ffd3c2ffd10fd2e2e2e2e2e2e2e2e2e2e002e2e2e2")
                .append("e2e2e2e2e2e2e2e2e2e3130014968b90024007c496861b0405258381137b9007cffc03859011607")
                .append("063106272207140716171617161514070e022306070607062206232227262726353412363332163")
                .append("3201716170714271406151417161736033534262716061514162734270607333625262726230306")
                .append("16153216333637363332163332363336352306272627363736053601302706070e0115141617363")
                .append("736026401070a35b80248022827250f010309433f01090d0b0f081020093c160f1211350a13061b")
                .append("07011b8b0a1e1b0e090506010bc3110101040d7b100405120701136969855231010311421006140")
                .append("3100924090e390e030914353b07080e2f010207feda170509010d0b010a15080292132034090206")
                .append("080e05050a3004113c16050404245b522c1a05070527241226022d120420033b09011c0f3f0a040")
                .append("5080145fef638041502081a060e139f04161f1a21900b0a0dfdbb030e030261b50e0403072f0109")
                .append("09086f030c0912fede1c38720d3d10031a03499235000006001e002302ae02ea003f00500058008")
                .append("e0092009f00a7404d01a0a040a1009e9a92908f574d27269a93908f6555514b402f3302695a5902")
                .append("3f002b026e7978022383021767102d95106b53053b4404787638046161043d3b2d046b7d041f870")
                .append("4981d0f011746762f3718002f2f2ffd2ffd2ffd2f3cfd10fd2f3cfd10fd10fd10fd012ffd2ffd3c")
                .append("2ffd2f3cfd3c2ffd2e2e2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e2e3130014968b9001700a0496")
                .append("861b0405258381137b900a0ffc038590114070e01232207060706070607062322272e01272e0135")
                .append("343736373633321716171615140f012706070615143332372627263534363536333216333233161")
                .append("72726272623220706070e0115141736373613262322071617363f01342635062726232207060716")
                .append("333215142322263534373637363736333217353427262726070607061514171633323e013736373")
                .append("60723161f0126232206232227161716333202ae03062709130e090a08071d3d2b5138412a6e1707")
                .append("0f2126515c6002593a1401071107883a60743b0b1b010e02117a0921072a29072bb90f070b181f4")
                .append("04808010f09304018fb0c08152e0812160f0206172428121656020109171169477034385718181e")
                .append("130d0b040e26372c6c48431846ae38473d030106067b13050a490e08155417374f133930242e012")
                .append("f182c0104100c1914143f1611180f60270d46116553624952021725021b144f0802182036707418")
                .append("210215140f3a0e1905022cc6150507151714040f0516092e1206fecd1f070b15093a2f071705010")
                .append("4050c173402196461465b4b5010060507021614290402020d21746d774c2871184d23181f091107")
                .append("0db7071016110b0a00050027003c02aa02dd003f0050005a00840090008140360191914092008f8")
                .append("77c7672605d59534b43393412108b857a7870685b32180e44020b0b02474003002e02515702646a")
                .append("05272705011846762f3718002f2f10fd012ffd2ffd2ffd2ffd10fd2e2e2e2e2e2e2e2e2e2e002e2")
                .append("e2e2e2e2e2e2e2e2e2e2e2e2e2e3130014968b900180091496861b0405258381137b90091ffc038")
                .append("590114020706232227262726353436352627060726272e023534373637363736373637363f01263")
                .append("3321716171e011514070607161736373e0133321632161716073426270316061514171617363736")
                .append("373625262706070607161736252627060f012623223534373637262322070607061516233637363")
                .append("3321706071633323637363736373605342706070615141716173602aa510c1413123a3f071c1511")
                .append("3f1832134b4e0917060701091419070405070e09083355160a1c0910090b012629060b0a0e19155")
                .append("1291110101b10044a010f05070116130e0f0cfecd09100a090d010b170b011f2a54091204583045")
                .append("0e0a0940180b1216212373060a11150e7d4c0e17460d1f0b070504031524fec2210913140907083")
                .append("901e819feb61b2e0809064f101450130808448902171811480a0c151a083b5e77211a1a1e120401")
                .append("0904290c1511101f280703070d4b440b0b0d534d1f0e5908fe8f0a24050a1217053557474829c51")
                .append("01617253607030520470607376e040f12183c2c2b050540a0a7351f2040651449940a182e202010")
                .append("4d86c80a27123d400e0e130f10ac000005000d0007024a0317003a006c00760083008d009c40410")
                .append("18e8e408f008c88827b76716b5856504240363216128a847c736d6968544e4a2c261a0e0a0c0e06")
                .append("68666668505206201e1e203c3b030077026222010600011a46762f3718003f3f012ffd2ffd3c872")
                .append("e0ec40efc0ec4872e0ec40efc0ec4012e2e2e2e2e2e2e2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e")
                .append("2e2e2e2e2e2e2e2e3130014968b9001a008e496861b0405258381137b9008effc03859251407060")
                .append("7060726272627343736372627262706070623222726273637363736373633321716151406070e01")
                .append("071617161716173637363330171e010735342726270623222726272627262734373637262706070")
                .append("6071617363332171617161716171615140706070607153417360334272635060714161713262726")
                .append("27071617161716173607262726350615141736024a20497353960e0d0b0a21363e100c0a0a0c413")
                .append("d03070a07080a17082568bc43040d100d14050f3f0f040e12040412052c20110e081a1c09080857")
                .append("13120b0605080e0c2140272802123c7b07b2050b463a0e060a080506031b141c17162a3a1f80f10")
                .append("70b04050e01e8021a1c051102080b0204180b6e010e150b2205a50a3f151611191529202011470f")
                .append("0c3f584d4c010c0a35162c13230a05112a0f352f130a4203060b0622354512244a010a08040d691")
                .append("106151e1819133b26261933376f0f0e080744140f1a011d1b36120a10342625125b452617080403")
                .append("080d0804661901df0e182501060c045702fed43a626c2e151422290d1ca302b90e2331012103044")
                .append("e05000004000f001d027703150030003a0048007300a7404b0174744075005c514f4e4a41363123")
                .append("2118604c4b494741362813064a494a4b062c29292c494a064a4b37363637700200323103003b030")
                .append("86803451d025562050e1b0547582c010e00011346762f3718003f3f2f3cfd10fd012ffd2ffd2ffd")
                .append("2ffd3c10fd872e0ec408fc0ec4872e0ec40efc08c4012e2e2e2e2e2e2e2e2e2e002e2e2e2e2e2e2")
                .append("e2e2e2e2e3130014968b900130074496861b0405258381137b90074ffc038590114070607060716")
                .append("151407060706232226272635343e023330163332353427262706072e03273736243332161716073")
                .append("53427262717161716033426272e0127161716151407360327051514173336333217161514062322")
                .append("27262706070607163332373637363534272627262726353437360277030e261d1c3d3e32664a2d3")
                .append("97a1e173e113b06801c3012111211360d390f1f01090e018c0d083804091b0208290603090a1931")
                .append("0a07140a131b1c142a0d16fe9223065f0a1f241e493d292e262608120c0d3a92254944212b18141")
                .append("4010c0b3622027108090c0d0909c7625f372c1a133d2f2303084a0a186e4e384c40410119032b18")
                .append("73100d064b390a1643162806122719192e0afe7f1dfc100a0a0632898c2f1c2c3101d2684d07175")
                .append("2219c88373a3b211d1d0318111071100e19213c3b685151072825020d0c07000400250013026802")
                .append("fd002b006000690074007f40360175754076006c67635f5c503f3b1e1c0602706a6545383426231")
                .append("208065602005902002c020061030072040c43041616010c00011246762f3718003f3f10fd10fd01")
                .append("2ffd10fd10fd10fd2e2e2e2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e2e2e2e2e3130014968b9001")
                .append("20075496861b0405258381137b90075ffc03859251407222726271615140e012322262726023534")
                .append("3e0133221716171617363332161716151406071617161716273427262722272635343736372e012")
                .append("7060706072627262706071617161716171e01171617363736373635342635343633321617361734")
                .append("27060716173e01072623220706071633323602685721241c1b130d98080d3c08067f0b850e03151")
                .append("427111e3c0e0e78061440090123301123323b370d01100e2811201832191c1d2f1411201f020977")
                .append("0122060f0813150c05070a43072d1a03280904088e095416052c1d0508053cca1a030335380d0b0")
                .append("c086fbb0d4b241e1e5d1b0c07252d0e0a024f17090c2a030c223a51a0460d28160db90c0c212c16")
                .append("2c06033f3a0e0e0b02105e2a6210230a44436b081e7e790202210b902a5426454a401e26160a020")
                .append("90c09051c721e040ba901492a050e182b050401356515101002161e000004002d001e02ad030200")
                .append("2700300040005e00604024015f5f4060005b504a3f382c281f5352413938312d2c2813002105444")
                .append("317010a00011346762f3718003f3f2f3cfd012e2e2e2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e31")
                .append("30014968b90013005f496861b0405258381137b9005fffc03859251407060706070607062322262")
                .append("72627262726353637363332171617161716173633321716171e0127262726271514171625262726")
                .append("27262f0115141716151617360526272322062322062322272627262706231514171617161716173")
                .append("6373602ad34222263b50a201a0f1043031013140c04025537201e420d160303040e8e3c131f1c06")
                .append("041521030f070d0d09feda0d0403020d1027090f0a140c0121080e1c2aa7280512050b08040d0a1")
                .append("4662a0d11020819060724477577100904041017020807390f61adbf4f1c4a010f0a4e4283333348")
                .append("17271d1b130a7b25223a060c0c2c1d086b233a2f30418526072136510555a8035e25492a0342468")
                .append("d458b120d2147610b6edc1225060e1e00060007001b031602f900410049007c0086009200a00091")
                .append("403f01a1a140a23b9b939189837d706c66604e482e17110b079b8d837d765e4a423b201e0706048")
                .append("70272460293780500434205314c053154042828010000011e46762f3718003f3f10fd2ffd10fd3c")
                .append("10fd012ffd2ffd2e2e2e2e2e2e2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e3")
                .append("130014968b9001e00a1496861b0405258381137b900a1ffc0385925222726273637350607062322")
                .append("2e0127262706070607062322272e01272627263736373637363736333217161716173e013332171")
                .append("617161716171615140706070e0103230e0115141736052627062322272627262322070607060706")
                .append("0706171617363736373633321716171617363736333215140706071617363712370706070607060")
                .append("73637362726270607061514171617360306070607060706073637363736025f09544b1914330519")
                .append("15060b5b120402020b19051619130633381911140605050b0b0e10010c3e6cb30c060506010f202")
                .append("20a49580614141b06032a2c1906395a140a1d0321010244874b150d0f1008641d18420d08060603")
                .append("140f01145a1414220a04060e180f10122622213809121a181849360b043b1ed70b1405191707141")
                .append("81da30b160109050809020abd240c0307102102050e1d070d0f1b28240e5e7b05031f1c190d1410")
                .append("103f7f14191b1113050a0b0a092664647156132255170b141c0521191d21020606090c07153fccd")
                .append("12e0a3002a00a340c0609353017348547500b0f0732463d3d196b513406206565a60b035c3a3a05")
                .append("07363659204a66565617141916010aa2ee050e0652491a0c0a585c2e5b0122150f101a220907016")
                .append("d1b15062f56e31f38050a274d7b00050008000d027e0308003d0043007b0083009600a540470197")
                .append("97409800958b837e72665a5449403e2a268d847f7e7c6a5d5c4d423e21006264091f1d1d1f91930")
                .append("61f1d1d1f700252514544023b0c06057878050910051635011900012146762f3718003f3f2ffd2f")
                .append("fd10fd3c012ffd3c2f3cfd872e0ec40efc0ec4872e0ec40efc0ec4012e2e2e2e2e2e2e2e2e2e2e2")
                .append("e2e002e2e2e2e2e2e2e2e2e2e2e2e2e3130014968b900210097496861b0405258381137b90097ff")
                .append("c038592514070607062322262322062322272627060706070623220623222726272627263534363")
                .append("7363332171617363736373637363332363332171617161714162726270615141335342726270607")
                .append("06071617160715062322272627262706071514171e0117161716333236323726272627263534333")
                .append("21716171633323736073427151417161727342627262726270607161716171617161736027e0311")
                .append("1e17300c3b12092508140f0c0b020511170f39103f0f150c050509111a3c0768440c1c111112070")
                .append("f140d352f1a04100414160f0c090a06db1c1019fb1b0520142b2222021a17020806111d1111482d")
                .append("6535070903130a1605050d381b14050a021a110f0d472a29090521393dbe67211b1bec0f01090f0")
                .append("4140a18030c1002060f090c17a4090c3f171203051f17180911300b08093f292a478ec322085302")
                .append("1c2b1c1c2d0d1e150d0c0b0884548d71710413d38e941e1537fe780730db2ffe0a07050638998c4")
                .append("40d07412a2bae551303070e1d240ecf52a40209081e3b06644129148c5454020e0e2d09bf08314c")
                .append("3c3b160d370c418362b11522234a610c4181254c1c0006000c002f0212030d001e002f004c005b0")
                .append("06d007700734031017878407900756e38726e38305c024d3e030f0e1f0200270234636202554a2c")
                .append("0508600557230419670551081901010e46762f3718003f2f2ffd10fd2ffd10fd3c012ffd3c2ffd2")
                .append("ffd2f3cfd2ffd2e2e2e2e002e2e2e3130014968b9000e0078496861b0405258381137b90078ffc0")
                .append("385901140706070607062322272627263d011437363736373637363332171617160734272623220")
                .append("7061514171e01333237360526272635343736370607060706070617161716171617161716333236")
                .append("131407062322272635343332171e010734272623220715141716333237363534361726070607221")
                .append("6333e010212242d4b1718141b63422422210f0412123a311246194e3e301f181f32436e69301f7f")
                .append("10431453342efed6452724040313080c09090901010d0b0b040b0a0a100a04150425d814192f461")
                .append("c0f3c200c263f42151b25100912161510100f022a0a14190301110f08140176495266220d0d0a33")
                .append("1b5c56355e044f1d3f2b352c041458456e5845647ea97c526aaa670d1a50458c305850563a1a164")
                .append("0051724232824323b2b2b0f1612131f05030a011731222b683b4c840c2393321e35450e4f133e4a")
                .append("201c15020a2802151a010406240007001200130216030600240030004c005500650074007e008d4")
                .append("03f017f7f4080007d786d665a4f3b2d266b514d44392b251b6002560a023e3d0702417a025d5b66")
                .append("75035631020053041337042169046476047321011300011b46762f3718003f3f2ffd2ffd10fd10f")
                .append("d012ffd2ffd3c2f3cfd2ffd2f3cfd10fd2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e2e3130014968")
                .append("b9001b007f496861b0405258381137b9007fffc0385901140607060706151416151407062607060")
                .append("7062322272627260326353437363736333217160727070607060716173637363734272627262322")
                .append("0710133637353426353426353437363736373e01032623220716313236131407060727343534263")
                .append("53437363332072e0123220716233237363736333217270627061514161536021650412242010513")
                .append("0d320b0620180d0c1714070c1110050c524128954c5737170c2b341e4a170838373b32322943320")
                .append("d9f492c4244060a1f15152f322f4af319020e75210d548e3a33390c0c24072f641a062713102823")
                .append("010508061411090b0c11142903044d01f34672140c1603041c721c26050401020109071d19101c0")
                .append("10cf55314070f0b083a43fe020221130a10170f141317e93f2c26120d18fef4fea30c0607165816")
                .append("0a2609100502020814137efe362611271301fd35241e020b011e1659161404013c1016092305010")
                .append("202250c0102080f0a250a1e000700010016021b03030022003f0043004e006d0079009100b84056")
                .append("01929240932176534f4d4643413c3a09918d8b7a7872584d43413e25211d4b4d094141424040415")
                .append("50e837402626a02621102323202132a021b468302627005667e056649040d2e041736044489045c")
                .append("17010300011146762f3718003f3f2ffd2ffd10fd2ffd2ffd10fd012ffd3c2ffd2ffd10fd10fd10f")
                .append("d10fd872e08c40efc0ec4012e2e2e2e2e2e2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e2e2e313001")
                .append("4968b900110092496861b0405258381137b90092ffc03859250e012322272627262706070623222")
                .append("7263534373637363332171615140716171615142726353436373635342726232207061514171633")
                .append("3237363332173637260f013f010522271e013332373637063722272627061514161514070623222")
                .append("7262726353437363332171615140706032623220716151417363534073427262322070e01151417")
                .append("161716333237263534373617020b05490d05160e0f0804193e302d1a1f7e03053a4567734539100")
                .append("60e3769020f0103333e64262479131a86305711030c3d12231714032904fee82424091517702801")
                .append("04414208150912273b2e29151c111b0e0a1b254c472118050734051114053d0d032c171d381e080")
                .append("40a080b140a11140a1f21231b6c0e48150e0e0702150c090e37e02c3972667b826c7c6e1f060c31")
                .append("1a196809010b2b0a1b3670647a1852de2471952e0932172d1577132a17310b140c2409122ad31f0")
                .append("e0f18070248031114110c13503a37584057543c5026263d011d030a24a70f0d251ba1a7412f3c25")
                .append("1f3e192d384d0b060620111a2d310700070001fff701f0031700330064006a00730083008f009c0")
                .append("0b34057019d9d409e0098938d6965575352501184716b675f5948062402137d0274900274080250")
                .append("6a17150313025034020096027a8c8a029a7705814d051c8605817204203c042c4a0471706b002c0")
                .append("11900201e1c030d00012446762f3718003f173c3f3f3f2f3cfd10fd10fd2ffd10fd10fd012ffd3c")
                .append("2ffd2ffd2ffd173c10fd2ffd10fd10fd2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e2e2e313001496")
                .append("8b90024009d496861b0405258381137b9009dffc0385901140706070615161514070e0107262726")
                .append("27061514151615142322262326070623222e0135341312373637363332171617161716073427262")
                .append("7222726232207060706070607060706171633321633321633363733161716173637262726272635")
                .append("343736373603363726270f0126272e012b011736011406232226353436353437363332160726230")
                .append("6230607061f013e012734262714061514173633343601f03b0e211c64090c6215181f250d03011d")
                .append("0a2a09080e11050f152c282a0b051a151013232a0b75313d1f131e39016f53070c15070d1011120")
                .append("701050501060b041c030f241111080a0e3b46081c380b1a022a2328301b289405042b3c09230205")
                .append("08530c1a1427010a643c13100b240832253522180f14020b01030301182e64170104030c0a03023")
                .append("1704410161301a128220d0f35051c3a47150a1b0a16170c550a0101010b460a2c012f0136140a07")
                .append("060809020e283144152033121610072953727283610a201b0f03040690910e738a0d10231e3b054")
                .append("3380b022931273afe0903045d7109ec090f030b1d01023d3b520b131b6e1c19060130311201030e")
                .append("18320f043a1c0427030f3c101a2002104500040011003201ea0310003a0044006f007d0086403b0")
                .append("17e7e407f00766651413b2e1076696852433c3b0e2b0e6033024d450200701602605a022031054f")
                .append("5605257c04061304646b047274062501010e46762f3718003f2f2f3cfd2ffd10fd10fd2ffd012ff")
                .append("d2ffd3c2ffd2ffd10fd2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e3130014968b9000e007e496861b0")
                .append("405258381137b9007effc0385901140706070623222726272627263534373216333236353427262")
                .append("726272627263534373e0133321716171615140623222623221514171e01171e0103353426272623")
                .append("2207161334272627262726353433321737342726232207061514171617161514070623222706071")
                .append("5163332373e01072627162322271617161716333201ea09070828ce1c384507050a1c3d0c7a340d")
                .append("20120e0e3b3a33201e0f10a62e2c343c130f2b1201601d10302344160b2b6902011d07161927602")
                .append("4133a47142c5e2e331c3a3414275e4f494444491d192240650e1f535d3d2034427b1a11315d5458")
                .append("100c16312d194e010d131a1516830708090d183b0625704d1f0d0e0704051c1d20443f271e24286")
                .append("11416211a431237240c161e1325231168011407030d020f0f01fece4f2e181d230f222d3f16390d")
                .append("1312372f454e2c201f28422113114622480629070a5a940c0102241e17010606000004000700090")
                .append("2970300003a0068007300800096403f0181814082007b756e6a6762570c087b75746e6a69605d55")
                .append("4a491f1e7779061f1e1e1f797b061f1e1e1f5002263c3b0200454404311a04182f0118000126467")
                .append("62f3718003f3f10fd2ffd3c012ffd3c2ffd872e0ec40efc0ec4872e0ec40efc0ec4012e2e2e2e2e")
                .append("2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e2e3130014968b900260081496861b0405258381137b90")
                .append("081ffc0385901140706070e022322272627060706070607060706070623222726272637132e0127")
                .append("262726353437363736373e013332171617161716171617160735262726272627262b010607061d0")
                .append("1161716171615140615060716173637363736273426353633321633321736253506070607363736")
                .append("373617350607060706073637363736029711080b0308280821293605060f050e0a0b0614061a1e0")
                .append("c132b1d1d2302490d2712212133080903050f0234053e270d4c59232e61203a0a1d20381d1d3b7e")
                .append("593135030a0a2d68100f150b1035284c1924060c0301030111021c052e6b18fdda0f04080f0a0b0")
                .append("70807a8041b011a0f1f0f121212240287294811180e101c0c11013d72212921201b350a191b140e")
                .append("0e0e150199130f0609090f1103151a0c1d39061703011317070815050f032817030c0a050a1e160")
                .append("e2621031102260304050d022907a1e30e1947892384220e0112030d09154d7d0a05053236050a12")
                .append("231fbd08020e068a5fad0a12396bd20000020013003e02bb02f8002d005800584021015959405a0")
                .append("045424030283e2e1c0022023849021124053651050a0a1501011146762f3718003f2f10fd2ffd01")
                .append("2ffd2ffd2e2e2e2e002e2e2e2e2e3130014968b900110059496861b0405258381137b90059ffc03")
                .append("85901140706070607060706232226272627263534373637161716171e0115140706070615163332")
                .append("3736371617161716272627060706070623223534373637363726271623222623220706151417161")
                .append("7161716333237363736373602bb2e0c0c0f12486f2c2f52922d0c0a0823252936354e0c05211c1d")
                .append("0309012222361e1e407d0a0a0947295116281617253f5a10100c0d13161e2056051005172b211a0")
                .append("b191b34302a6f524b16031e1501ff327c2120241964240d474212312c1c53899436040408140748")
                .append("04035a600d281e37a66768091b1234305f050b43874e233a7a204c48231f4009010108a87f73143")
                .append("5182619110f554d71106449000003000c00030289030f003b00570067007b403201686840690065")
                .append("625852463e372f5f59584e3c281a004e4f06161515162b02484e051c504f045f5e221f011c010c0")
                .append("0011a46762f3718003f3f3f3c2f3cfd3c10fd012ffd872e0ec40efc0ec4012e2e2e2e2e2e2e2e00")
                .append("2e2e2e2e2e2e2e2e3130014968b9001a0068496861b0405258381137b90068ffc03859011407060")
                .append("70615060706070623222726272e0127262703262726353433321633323633321716171631140615")
                .append("1417161736373637363736333216171627262706070607060706232227262726272607133336173")
                .append("6373637360135342637262b011e01333a01333216028902022a352a5b1b1a2b1615404509042405")
                .append("030532060708130a270a0b2e0c22140c171204060605371a2c1d081505080d93040a2711720d241")
                .append("f1f202036080c010710060b7f144908376d396a152b20ff000a015b172e0b181e061f060c25023c")
                .append("02070a425201509c2f2f47070906023f13092c01972f303827180b020f0a231d071b072f483c3b5")
                .append("32b473b1729054108160b0a2a2a3a302f37375b0a61ba2d5a1201fd95011251a62a5237fe070707")
                .append("1e090f162a0400040015005202f802fb003f0073007c008600874038018787408800766c6561575")
                .append("04842352b817d79745b5a401e005b5d061e1c1c1e7b040a8404144d042f560421810e0479780a3a")
                .append("3901011e46762f3718003f3c2f2f3cfd3c2ffd2ffd2ffd10fd01872e0ec40efc0ec4012e2e2e2e2")
                .append("e2e2e2e2e002e2e2e2e2e2e2e2e2e2e3130014968b9001e0087496861b0405258381137b90087ff")
                .append("c03859011407060706070607062322272627060706220623222726272627262726273e0133321f0")
                .append("11e0117161716173637363332171617161736373637333216171e01272627060706070623222627")
                .append("262322062322272627262f012606271516171617161736373633321716173616333237363736373")
                .append("603262706072316333227262726271e0133323602f817111101130f0b097c2b120120100f14274f")
                .append("141a23200a0d0d1006172e0b72110324060e35080c0d100607070618262b0a0d0f050d191e0a040")
                .append("b8104071d43224315151f130a050c28051d1b033e1411130b0a1c1b0c164216142c0e26376c1010")
                .append("190a0b170c0d0c2d093f0a14171010093f070a3050040f1b02ad0509097e0c19180e3b024429594")
                .append("142017254151115013d16161c0225211d264d5c1a61c30719020103230d11616e1411100912050f")
                .append("1404416f88163c060b5d5b112162628e241471020a81643e3e732d010115010756b1509e060a222")
                .append("1353b24240103193482616127fe170b180d011c0e0b0e040c111d050000050019001002d902f100")
                .append("2d0056005f0067006f0088403a0170704071006c6b6865605e594f3b363228246c6968666557544")
                .append("23f2e180400616002084402165102085a59021c4b050e460511200b00011646762f3718003f2f2f")
                .append("fd2ffd012ffd3c2ffd2ffd10fd3c2e2e2e2e2e2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e2e2e2e2")
                .append("e2e3130014968b900160070496861b0405258381137b90070ffc038590114070607161716151406")
                .append("232226232206232226272635343726272635343e013322171617303736333217161716273427262")
                .append("7060706232226272627060706151416151407161736373e01333217161736353426353436052627")
                .append("15141e0133320135342726271516053526312314171602d9432e2e27261a7d150c92010ec00a0d5")
                .append("804167c1d1e1f0e940b010d543476600e112d2a070a30201d0d126964060a143524220f3238819d")
                .append("2e233060023802064d2e2f507cbcfe162d300b40040601483826262bfed53701100901e61637222")
                .append("32f2f2c4b135c98705a104423156526262e2a3a1a7601625f5e4d3633161c0e0a28250c0b5d581e")
                .append("563b2b0b2c2f0402b50c08873f251b360126472b2b4a0504b5040aa89d3a4313121350fea809163")
                .append("a2625192f261e4620180f0003001300060278030a0032005800650090403e016666406731623b37")
                .append("332a6460553d31213335062c2a2a2c3d3f06211f1f214d020818034646021542021b494705605f5")
                .append("c03592e012501100d0a00012146762f3718003f3c3c3f3f2f173cfd3c012ffd2ffd10fd2ffd872e")
                .append("0ec40efc0ec4872e0ec40efc0ec4012e2e2e2e2e2e002e2e2e2e2e3130014968b90021006649686")
                .append("1b0405258381137b90066ffc038590106070e0115140607062322262322062322272e0135343635")
                .append("343635342726272627363736371e011716173637363332161706270607062322272627060716171")
                .append("e0115140615071732333237363f01363736373e01353427260322062322262b01161736272601fe")
                .append("15151b0e14030c300a24060b2e0b1b0a041d080c100d0c01710247421440241108121637450c1f7")
                .append("901454f1a424f0e0c09214b046f204311230c0806096b1a01050a0804020119149e231f9c0a2509")
                .append("081c0733071098090501ca17181e284411e0041502030c0550061b6e1b134d13131a151401e40f1")
                .append("e1d01222a411f3c24566a72206dda2a687b1357ab012d42841a381e134810a7030309885f2e0601")
                .append("120fd810081a16fd7502040f1b02011200040006000f025c03130032003a0043007100884038017")
                .append("2724073006a695c564a3b37332a67635a544c44403f3b37332a26150c0b00333506262323260c05")
                .append("611005262e043f461f010500011546762f3718003f3f2f3cfd2ffd2ffd01872e0ec40efc0ec4012")
                .append("e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e2e002e2e2e2e2e2e2e2e2e3130014968b900150072496861")
                .append("b0405258381137b90072ffc03859250706070623222e012726271306070623222e0235343736373")
                .append("63736373633321716171e0117060706073637363332171e01032627262716171613262726271514")
                .append("1716272627060706072635343736373637363534270607060716173633323633161514070607161")
                .append("73336373637363736025c0149829c290a3718151204c8193f3b1a093508161310102fbb0549331c")
                .append("14171b08011d010a3229540c443e0608340612480c0d07170512024f040b08180f091f0510215e5")
                .append("22b113630102646061520ac7d4e0b0c1f050fb228116c4e3d01270628344415293b5dba09283743")
                .append("1c11403819014a0218173b19690b0f0604030f1e01100b0a0b0f019c07134c4386031715270c580")
                .append("18e42460a0c354d04fe651a2a100f0a27190f031d380b1a1613040b055c501b34690b080c740619")
                .append("12143335073d070917b4835e0f6a0f0e12070d1c2b00ffff0018ffdc010f03020006000b0000000")
                .append("30014003e015c02ec001e0032003a004a4018013b3b403c003531282737332a1f0d003904031103")
                .append("010d46762f3718002f2f10fd012e2e2e2e2e2e002e2e2e2e3130014968b9000d003b496861b0405")
                .append("258381137b9003bffc03859250e0123222e01272627262726353437362332171617161716171617")
                .append("16171627262726272627262723060716171617161317361726232207162332015c043d100e270f2")
                .append("3151503352e075408130e0512030a1b1b1c0b0629243a1a1a210c0f200e23070d1a0205111e2851")
                .append("040d4a10020d2a1b0409600b171315804e4f0aa48d0c0505181507230e1e4c4b503c1e62570f3a6")
                .append("27a232453305b060c0d1b2d5987fef1030815101b09ffff0013fff900f303150006000c0000ffff")
                .append("002e0045021f02a7000600060000ffff003a0011021b00e900070010fffdff080004003501b0013")
                .append("103560024002f004c00570077403301585840590a51432b52514d4327154602292e2503180e0c03")
                .append("0a31300329390223494b051a56041a37350402041a2701012346762f3718003f2f2f2ffd3c10fd1")
                .append("0fd3c012ffd2ffd3c2f173cfd3c10fd2e2e2e2e2e2e002e2e2e3130014968b900230058496861b0")
                .append("405258381137b90058ffc0385913363736171633321716151407141514070623220f01061635142")
                .append("3222627262726272635341734271615321734363514073534272623223306151416061716171617")
                .append("16172e013534361716313217262726271514171633323f070b080d14024d194f010103110a0c010")
                .append("22f481861040b0b160506e11d040b0b033507102a49060b0501050303030d1f3f08220c0a0e0840")
                .append("20060a1614150c07034b07020201010849281414151422041606081864011541181e1e3f28312c3")
                .append("8690d22453a04061e0508103660040a150e052222190f0f2225571b0b8c120a050202c5373c040d")
                .append("0b11363900ffff0000001502a8030d000600240000ffff001e000502310309000600250000ffff0")
                .append("0170067022902f7000600260000ffff000b000f023f0313000600270000ffff0007000a0225031b")
                .append("000600280000ffff001b003402640302000600290000ffff001e002302ae02ea0006002a0000fff")
                .append("f0027003c02aa02dd0006002b0000ffff000d0007024a03170006002c0000ffff000f001d027703")
                .append("150006002d0000ffff00250013026802fd0006002e0000ffff002d001e02ad03020006002f0000f")
                .append("fff000c001b031602f9000600300000ffff0008000d027e0308000600310000ffff000c002f0212")
                .append("030d000600320000ffff0012001302160306000600330000ffff00010016021b030300060034000")
                .append("0ffff0001fff701f00317000600350000ffff0011003201ea0310000600360000ffff0007000902")
                .append("970300000600370000ffff0013003e02bb02f8000600380000ffff000c00030289030f000600390")
                .append("000ffff0015005202f802fb0006003a0000ffff0019001002d902f10006003b0000ffff00130006")
                .append("0278030a0006003c0000ffff0006000f025c03130006003d0000ffff0018ffdc010f03020006000")
                .append("b0000ffff002b0051015d02dc000600120000ffff0013fff900f303150006000c0000ffff002e00")
                .append("45021f02a7000600060000001d0012fff30328030b00300056006c007c009200a200a900b200c20")
                .append("0cc00db00e300e600ed01790185019901ab01bf01d501df01e501e901f201fe020a0213021f0226")
                .append("0000011407060706070607062322062322062206070627262322272627262726272635343736373")
                .append("6333216321633321716171607342726272627262322262322070607061514171e01171633323632")
                .append("36323632373637363736031406232227343736372e01353437161532173637320714232226233c0")
                .append("13516151636333216270e011514173237331407062b012227263534333216070623223534373637")
                .append("3634373217163527220623263733171423222735323716271407061522062334263534373637161")
                .append("7060730263530363317270607060726273236333216173e01270723352636353201273725060726")
                .append("37273301140706071407060706070617062322262322062322262322073e013332163b0132373e0")
                .append("1352635363736373637141736353426353437362322071633060706233227363534271e01333237")
                .append("363715333637060715301633303736373427262707060736373637363723060706070607062b013")
                .append("633321633323736373637363332171617161716071e013316250623223734373e01373316011407")
                .append("06072206232227263534373e01333217160f0126272623221514170627352636333215072226232")
                .append("2151432170623222635343633321615270607163332171526230722273536372e01273516171335")
                .append("2623220715163332273627071533172707172734272615141733360135222623221514173637362")
                .append("53427061514161532363332012226232207163332173423220615163332343332272e0123220732")
                .append("0328141e2417200a28331102280d030b080f032a2b0e0a1e382b1d2828361211545b981e4f040e0")
                .append("7300314505b2f2812130f353a3d370804420477516241301723a1631c4104100809082f042a4532")
                .append("191627922006050e01050c021304130305060c0b43190417050f030b0308118b07240805090b010")
                .append("50a0b0d08062c040b3e28071113071003050108054a010b0107060ec60c040e0a0d07bf0601010d")
                .append("0303070408087f081203100a039f0505060b0f110105040409030b0b39040e01070801520b05feb")
                .append("a050f0104031401c207111e0512241e1f0602250b031c04044903224e131628065006083610271b")
                .append("200502023663190d0304070b0c0c020d111c01030808202604461f12051c040a070404180b18080")
                .append("701051c062104365c06123f14170a0a0e0532052c311a032f28064114030402031d0a16332d223f")
                .append("0f2f30261a0d18060a031a0118fe2329060f02170c05060b0301880a0306020c05120b090204210")
                .append("811070555070207050515050d08011b05204f0623030a2f0408101417161c060e52112321040b04")
                .append("230f09060e0c28032d041d32ea070504080806068f030d070e6c110211a60a0e040d070124050e0")
                .append("40a07010c0dfe200b10030102041101300516030c07080b12670f0d110411040311a1030f030f03")
                .append("2801804c364f2a1b1c09191f0f0304010303011a13141c2e3e474435896c751b06060d2c3c60545")
                .append("c2b4a384248191612252f6f5263423d61880e0303030f13203e202c4f0151084a07020402080c36")
                .append("0304040c1c060c19171d040d390e081101051e0a08020905080908030a100c072614340a1b160d0")
                .append("2080a04050512010403060ce20b070b0607b902070b2a03030b041820010408c3050b0f090303b6")
                .append("1313160f05340414080325020e09050201fe8d051ff909072e1b05fee7180a1024050a160201010")
                .append("405170f0c210b0720310d02030204030205020f0328050d0f090423040b0c071903090107140211")
                .append("0d0b020d0c0a091f0609080e110314040e070a402a060315110c0302050a0113150f02221e0c030")
                .append("613251d0e191f1a220d21090a060614bd191a1717020c111afe1c17130405071712140a070c171a")
                .append("0f0407010f0b2c1b1307044f061b1723030922040b24111d15160611112709040a0503070e14220")
                .append("707070a060901c91d07071a07260806070b950a040a87110103190b0708fee81403120b1401030b")
                .append("f50f05130b040a0102feba040e045827181126032c060e14ffff0020013800d801ec0007000dfff")
                .append("7ff08ffff003501b001310354000600430000ffff0013019c012b03440006000a0000ffff0013ff")
                .append("5c012b01050007000a0000fdc1ffff003501b001310354000600430000ffff003501b0025e03540")
                .append("0260064000000070064012d0000ffff0013019302630344002600650000000700650137fff7ffff")
                .append("0012000202e200c80026001100000027001100fb00030007001101f0fffd000000000000007c000")
                .append("0007c0000007c0000007c000002400000025a0000063a0000064a0000065a0000066a0000067c00")
                .append("00075c000008220000090a00000aec00000c2000000d7600000e4200000f3e000010e60000131a0")
                .append("000157600001788000019dc00001c2c00001e400000202c000021f40000246e000024860000249e")
                .append("000024ae000024c6000024d600002730000027400000298a00002b9000002d5200002f260000310")
                .append("8000032fa0000356400003794000039d800003bce00003da600003f240000419a000043f2000045")
                .append("c4000047c400004a1800004c9200004e7c00005094000051f4000053a0000055b80000578000005")
                .append("93800005b1800005b2800005c2c00005c3c00005c4c00005c5e00005dce00005dde00005dee0000")
                .append("5dfe00005e0e00005e1e00005e2e00005e3e00005e4e00005e5e00005e6e00005e7e00005e8e000")
                .append("05e9e00005eae00005ebe00005ece00005ede00005eee00005efe00005f0e00005f1e00005f2e00")
                .append("005f3e00005f4e00005f5e00005f6e00005f7e00005f8e00005f9e00005fae00006598000065aa0")
                .append("00065ba000065ca000065dc000065ec000066040000661c0000663c01f4003f00000000033d0000")
                .append("033d0000012f0024027c00100246002e0246002e0246002e0246002e0148001301280018011a001")
                .append("300f70029023a0018014800130246003c010b0012017c002b02510006022c002902740021020600")
                .append("100246001602680018023400180248000c023400030209000c010b000f014800130128001802460")
                .append("03c011a001301fb00010246002e02b60000024a001e02460017024e000b025100070282001b02df")
                .append("001e02c400270262000d0291000f0291002502ca002d0330000702960008023d000c02370012022")
                .append("e0001020900010209001102b0000702e4001302ad000c030f001502f3001902850013027c000601")
                .append("28001801760014011a00130246002e0246003a0159003502b60000024a001e02460017024e000b0")
                .append("25100070282001b02df001e02c400270262000d0291000f0291002502ca002d0330000c02960008")
                .append("023d000c02370012022e0001020900010209001102b0000702e4001302ad000c030f001502f3001")
                .append("902850013027c000601280018017c002b011a00130246002e0343001200f7002001590035014800")
                .append("130148001301590035027c0035027c0013030100120002000000000000ff7b00140000000000000")
                .append("000000000000000000000000000006b0000000100020003000400050006000700080009000a000b")
                .append("000c000d000e000f0010001100120013001400150016001700180019001a001b001c001d001e001")
                .append("f0020002100220023002400250026002700280029002a002b002c002d002e002f00300031003200")
                .append("33003400350036003700380039003a003b003c003d003e003f00400041004200430044004500460")
                .append("04700480049004a004b004c004d004e004f0050005100520053005400550056005700580059005a")
                .append("005b005c005d005e005f006000610086008800b600b700c4010200b400b500ab0d71756f7465726")
                .append("576657273656400000000000300000000000004cc000100000000001c00030001000004cc000604")
                .append("b000000000025300010000000000000000000000000000000100030000000000000002000000000")
                .append("0000000000000000000000000000000000000000000000000000001000000000003000400050006")
                .append("000700080009000a000b000c000d000e000f0010001100120013001400150016001700180019001")
                .append("a001b001c001d001e001f0020002100220023002400250026002700280029002a002b002c002d00")
                .append("2e002f0030003100320033003400350036003700380039003a003b003c003d003e003f004000410")
                .append("0420043004400450046004700480049004a004b004c004d004e004f005000510052005300540055")
                .append("0056005700580059005a005b005c005d005e005f006000610000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000620000000000000000000000000")
                .append("0000000000000000000000000000000006300000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .append("0000000000000000000000000000000000000000000000000000000006400650066006700680069")
                .append("00000000000000000000006a0004011c0000000e000800020006007e00a700b62010201d2026fff")
                .append("f0000002000a700b6201020182026ffff0000000000000000000000000001000e00ca00ca00ca00")
                .append("ca00d4ffff0003000400050006000700080009000a000b000c000d000e000f00100011001200130")
                .append("01400150016001700180019001a001b001c001d001e001f00200021002200230024002500260027")
                .append("00280029002a002b002c002d002e002f0030003100320033003400350036003700380039003a003")
                .append("b003c003d003e003f0040004100420043004400450046004700480049004a004b004c004d004e00")
                .append("4f0050005100520053005400550056005700580059005a005b005c005d005e005f0060006100620")
                .append("0630010006400650066006700680069006a0000000000010000001a000100020006000100060032")
                .append("0025ffd90032003cff9900000000001000000070090805000707030605050505030303020503050")
                .append("2030505060505060505050502030305030505060505050506070605060606070605050505050607")
                .append("0607070606030303050503060505050506070605060606070605050505050607060707060603030")
                .append("3050802030303030606070000000a08050008080306060606060303030206030603040606060506")
                .append("0606060605030303060305060706060606060707060707070807060606050507070708080606030")
                .append("4030606030706060606060707060707070807060606050507070708080606030403060802030303")
                .append("030606080000000b090600090903070606060604030303060406030407060706060706060606030")
                .append("4030603060608060606070708080707070809070606060606080808090807070304030606040806")
                .append("0606070708080707070809070606060606080808090807070304030609030404040407070800000")
                .append("00c0a06000a0a040807070707040403030704070305070708060707070707060304040703060708")
                .append("07070707080908070808090a0807070706060809080909080804040307070408070707070809080")
                .append("70808090a08070707060608090809090808040503070a03040404040808090000000d0b07000b0b")
                .append("04080808080804040403070408030508070807080807080707030404080407080908080808080a0")
                .append("9080909090b090707070707090a090a0a08080405040808040908080808080a09080909090b0907")
                .append("07070707090a090a0a0808040504080b030404040408080a0000000e0c07000c0c0409080808080")
                .append("5040403080508040508080907080908080807040504080407080a08080808090a0a0909090a0b09")
                .append("08080807070a0a0a0b0b09090405040808050a08080808090a0a0909090a0b0908080807070a0a0")
                .append("a0b0b0909040504080c030505050509090b0000000f0d08000c0c050a0909090905040404090509")
                .append("040609080908090908090808040504090408090a090909090a0b0b090a0a0b0c0a09090808080a0")
                .append("b0a0c0b0a0a0406040909050a090909090a0b0b090a0a0b0c0a09090808080a0b0a0c0b0a0a0406")
                .append("04090d04050505050a0a0c000000100d08000d0d050a0909090905050504090509040609090a080")
                .append("90a09090908040505090508090b090909090a0c0b0a0b0b0b0d0b09090908080b0c0b0d0c0a0a05")
                .append("06050909060b090909090a0c0b0a0b0b0b0d0b09090908080b0c0b0d0c0a0a050605090d0406050")
                .append("5060a0a0c000000110e09000e0e050b0a0a0a0a060505040a060a05060a090b090a0a0a0a0a0905")
                .append("06050a05090a0c0a0a0a0a0b0c0c0a0b0b0c0e0b0a0a0909090c0d0c0d0d0b0b0506050a0a060c0")
                .append("a0a0a0a0b0c0c0a0b0b0c0e0b0a0a0909090c0d0c0d0d0b0b0506050a0e04060606060b0b0d0000")
                .append("00120f09000f0f050b0a0a0a0a060505040a060a05070b0a0b090a0b0a0b0a090506050a05090a0")
                .append("c0b0a0b0b0c0d0d0b0c0c0d0f0c0a0a0a09090c0d0c0e0e0c0b0507050a0a060c0b0a0b0b0c0d0d")
                .append("0b0c0c0d0f0c0a0a0a09090c0d0c0e0e0c0b0507050a0f04060606060b0b0e00000013100a00101")
                .append("0060c0b0b0b0b060605050b060b05070b0b0c0a0b0c0b0b0b0a0506060b050a0b0d0b0b0b0b0c0e")
                .append("0d0c0c0c0e100d0b0b0b0a0a0d0e0d0f0e0c0c0607050b0b070d0b0b0b0b0c0e0d0c0c0c0e100d0")
                .append("b0b0b0a0a0d0e0d0f0e0c0c0607050b1005070606070c0c0f00000014110a001111060d0c0c0c0c")
                .append("070606050b070c05080c0b0d0a0c0c0b0c0b0a0507060c060a0c0e0c0c0c0c0d0f0e0c0d0d0e100")
                .append("d0b0b0b0a0a0e0f0e100f0d0d0607060c0c070e0c0c0c0c0d0f0e0c0d0d0e100d0b0b0b0a0a0e0f")
                .append("0e100f0d0d0608060c1105070707070d0d0f00000015120b001111060d0c0c0c0c070606050c070")
                .append("c06080c0c0d0b0c0d0c0c0c0b0607060c060b0c0f0c0c0c0c0d0f0f0d0e0e0f110e0c0c0c0b0b0e")
                .append("100e10100e0d0608060c0c070f0c0c0c0c0d0f0f0d0e0e0f110e0c0c0c0b0b0e100e10100e0d060")
                .append("8060c1205070707070d0d1000000016120b001212070e0d0d0d0d070706050d070d06080d0c0e0b")
                .append("0d0e0c0d0c0b0607070d060b0d0f0d0d0d0d0e10100d0e0e10120f0d0c0c0b0b0f100f11110e0e0")
                .append("708060d0d080f0d0d0d0d0e10100d0e0e10120f0d0c0c0b0b0f100f11110e0e0708060d12050807")
                .append("07080e0e1100000017130c001313070f0d0d0d0d080706060d080d06090e0d0e0c0d0e0d0d0d0c0")
                .append("608070d060c0d100d0d0e0e0f11100e0f0f10130f0d0d0d0c0c10111012110f0f0709060d0d0810")
                .append("0d0d0e0e0f11100e0f0f10130f0d0d0d0c0c10111012110f0f0709060d1306080808080f0f12000")
                .append("00018140c001414070f0e0e0e0e080707060e080e06090e0d0f0c0e0f0e0e0e0d0608070e070c0e")
                .append("110e0e0e0e0f12110f10101114100e0e0d0d0d11121013120f0f0709070e0e08110e0e0e0e0f121")
                .append("10f10101114100e0e0d0d0d11121013120f0f0709070e1406080808080f0f120000000001029c01")
                .append("900005000002bc028a0000008f02bc028a000001c50032010300000000040000000000000000000")
                .append("00300000000000000000000000046726f670040002020260355ff5c0000035500a4000000010000")
                .append("000000000001000080000000033d02f10000600002dd0275416374696f6e204a61636b732020202")
                .append("0ffffffff37fffffe4143545230300000000000000001000000010000fae7a6eb5f0f3cf5000003")
                .append("e800000000b2050dda00000000b20549240000ff5c0328035600000003000200010000000000010")
                .append("0000356ff38000003430000000d032800010000000000000000000000000000006b00010000006b")
                .append("0227001d01030009000200080040000a00000097013000030003");
	        return sb.toString();
	    }
	}
}
