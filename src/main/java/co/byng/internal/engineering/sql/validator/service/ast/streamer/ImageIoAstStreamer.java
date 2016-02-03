/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.byng.internal.engineering.sql.validator.service.ast.streamer;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;

/**
 * ImageIoAstStreamer
 * 
 * @author M.D.Ward <matthew.ward@byng.co>
 * @copyright (c) 2016, Byng Services Ltd
 */
public class ImageIoAstStreamer <I extends ImageIO> implements AstImageStreamer {

    @Override
    public void streamImage(RenderedImage image, String format, OutputStream outputStream) throws IOException {
        I.write(image, format, outputStream);
    }
    
    
    
    public static class DefaultImpl extends ImageIoAstStreamer<ImageIO> {}
    
}
