/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.byng.internal.engineering.sql.validator.service.ast.generator;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

/**
 * AstImageFactory
 * 
 * @author M.D.Ward <matthew.ward@byng.co>
 * @copyright (c) 2016, Byng Services Ltd
 */
public interface AstImageFactory <I extends RenderedImage> {

    public I buildImage(int width, int height);
    
    
    
    public static class BufferedImpl implements AstImageFactory<BufferedImage> {

        protected int imageType;

        public BufferedImpl(int imageType) {
            this.imageType = imageType;
        }
        
        public BufferedImpl() {
            this(BufferedImage.TYPE_INT_ARGB);
        }
        
        public int getImageType() {
            return this.imageType;
        }

        public BufferedImpl setImageType(int imageType) {
            this.imageType = imageType;
            
            return this;
        }
        
        @Override
        public BufferedImage buildImage(int width, int height) {
            return new BufferedImage(width, height, this.imageType);
        }
        
    }
    
}
