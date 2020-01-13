package fighter_image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Load_images {
	
	public static BufferedImage image;
	
	public static void init(){
	  image = imageLoader("/fight.png");
	}
	
	public static BufferedImage imageLoader(String path){
		try {
			return ImageIO.read(Load_images.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

}
