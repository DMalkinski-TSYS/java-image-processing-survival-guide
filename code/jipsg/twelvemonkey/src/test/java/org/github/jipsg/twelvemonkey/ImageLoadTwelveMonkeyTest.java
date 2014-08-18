/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.github.jipsg.twelvemonkey;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Load various images.
 */
public class ImageLoadTwelveMonkeyTest extends AbstractTwelveMonkeyTest {

    @Before
    public void setup() {
        super.setup();
    }

    // ======================================================================
    // General
    // ======================================================================

    /**
     * List available image formats.
     *
     * see http://examples.javacodegeeks.com/desktop-java/imageio/list-read-write-supported-image-formats/
     */
    @Test
    public void testListSupportedImageFormats() throws Exception {

        Set<String> set = new HashSet<String>();

        // Get list of all informal format names understood by the current set of registered readers
        String[] formatNames = ImageIO.getReaderFormatNames();

        for (int i = 0; i < formatNames.length; i++) {
            set.add(formatNames[i].toLowerCase());
        }
        System.out.println("Supported read formats: " + set);

        set.clear();

        // Get list of all informal format names understood by the current set of registered writers
        formatNames = ImageIO.getWriterFormatNames();

        for (int i = 0; i < formatNames.length; i++) {
            set.add(formatNames[i].toLowerCase());
        }
        System.out.println("Supported write formats: " + set);

        set.clear();

        // Get list of all MIME types understood by the current set of registered readers
        formatNames = ImageIO.getReaderMIMETypes();

        for (int i = 0; i < formatNames.length; i++) {
            set.add(formatNames[i].toLowerCase());
        }
        System.out.println("Supported read MIME types: " + set);

        set.clear();

        // Get list of all MIME types understood by the current set of registered writers
        formatNames = ImageIO.getWriterMIMETypes();

        for (int i = 0; i < formatNames.length; i++) {
            set.add(formatNames[i].toLowerCase());
        }
        System.out.println("Supported write MIME types: " + set);
    }

    // ======================================================================
    // Load various image formats
    // ======================================================================

    @Test
    public void testLoadVariousImageFormats() throws Exception {

        List<File> sourceImageFileList = new ArrayList<File>();

        sourceImageFileList.add(getImageFile("jpg", "marble.jpg"));
        sourceImageFileList.add(getImageFile("png", "marble.png"));
        sourceImageFileList.add(getImageFile("tiff", "marble.tiff"));
        sourceImageFileList.add(getImageFile("gif", "marble.gif"));

        for(File sourceImageFile : sourceImageFileList) {
            BufferedImage bufferedImage = createBufferedImage(sourceImageFile);
            assertValidBufferedImage(bufferedImage);
        }
    }

    // ======================================================================
    // JPEG
    // ======================================================================

    /**
     * Plain-vanilla JPEG
     */
    @Test
    public void testLoadJPEGImage() throws Exception {
        assertValidBufferedImage(createBufferedImage(getImageFile("jpg", "test-image-rgb-01.jpg")));
    }

    /**
     * CMYK color model is supported.
     */
    @Test
    public void testLoadCMYKImage() throws Exception {
        assertValidBufferedImage(createBufferedImage(getImageFile("jpg", "test-image-cmyk-uncompressed.jpg")));
    }

    // ======================================================================
    // TIFF
    // ======================================================================

    /**
     * Load a TIFF image with compression 2.
     * Fails with  "ArrayIndexOutOfBoundsException"
     */
    @Test
    @Ignore
    public void testLoadTiffGrayWithCompression2() throws Exception {
        assertValidBufferedImage(createBufferedImage(getImageFile("tiff", "test-single-gray-compression-type-2.tiff")));
    }

    /**
     * Load a TIFF image with compression 3.
     * Fails with "javax.imageio.IIOException: Unsupported TIFF Compression value: 3"
     */
    @Test
    @Ignore
    public void testLoadTiffWithCompression3() throws Exception {
        assertValidBufferedImage(createBufferedImage(getImageFile("tiff", "test-single-gray-compression-type-3.tiff")));
    }

    /**
     * Load a TIFF image with compression 4.
     * Fails with "javax.imageio.IIOException: Unsupported TIFF Compression value: 4"
     */
    @Test
    @Ignore
    public void testLoadTiffWithCompression4() throws Exception {
        assertValidBufferedImage(createBufferedImage(getImageFile("tiff", "test-single-gray-compression-type-4.tiff")));
    }

    /**
     * Load a multi-page TIFF.
     * Fails with a "javax.imageio.IIOException: Unsupported TIFF Compression value: 4"
     */
    @Test
    @Ignore
    public void testLoadTiffMultiPageGray() throws Exception {
        assertValidBufferedImage(createBufferedImage(getImageFile("tiff", "test-multi-gray-compression-type-4.tiff")));
    }

    /**
     * Load a TIFF image with compression LZW.
     */
    @Test
    public void testLoadTiffSingleCmykCompressionLzw() throws Exception {
        assertValidBufferedImage(createBufferedImage(getImageFile("tiff", "test-single-cmyk-compression-lzw.tiff")));
    }
}