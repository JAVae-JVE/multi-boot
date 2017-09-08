package com.jinmark.core.utils.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * 图片等比例缩略图工具类
 * QC
 * 2017年2月17日 下午12:50:47
 */
public class ThumbnailUtil {
	/**
	 * 压缩至指定图片尺寸，保持图片不变形，多余部分裁剪掉
	 * @param file 文件
	 * @param width 
	 * @param height
	 * @param fileSize 压缩图片文件大小
	 * @return void
	 */
	public static void thumbnail(File fromPic,File toPic, int width, int height, long fileSize) {
		try {
			BufferedImage image = ImageIO.read(fromPic);
			Builder<BufferedImage> builder = null;  
			//原图宽 
			int imageWidth = image.getWidth(); 
			//原图宽高
			int imageHeitht = image.getHeight(); 
			
			if ((float)width / height != (float)imageWidth / imageHeitht) {  
			    if (imageWidth > imageHeitht) {  
			        image = Thumbnails.of(fromPic).height(height).asBufferedImage();  
			    } else {  
			        image = Thumbnails.of(fromPic).width(width).asBufferedImage();  
			    }  
			    builder = Thumbnails.of(image).size(width, height).sourceRegion(Positions.CENTER, width, height);  
			} else {  
			    builder = Thumbnails.of(image).size(width, height);  
			}
			builder.toFile(toPic);  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
