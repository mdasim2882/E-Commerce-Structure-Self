package com.example.sweetcartapp.ShoppersRoom.Commons;

import java.util.ArrayList;
import java.util.List;

public class CartItemsAndImagesList {
    public static List<Integer> imageId = new ArrayList<>();
    public static List<String> titleID = new ArrayList<>();

    public CartItemsAndImagesList() {
    }

    public static List<Integer> getImageId() {
        return imageId;
    }

    public static List<String> getTitleID() {
        return titleID;
    }

    public void addImageId(int imgno) {
        CartItemsAndImagesList.imageId.add(imgno);
    }

    public void addtitleId(String titleno) {
        CartItemsAndImagesList.titleID.add(titleno);
    }

    public void deleteImageId(int imgno) {
        CartItemsAndImagesList.imageId.remove(new Integer(imgno));
    }

    public void deletetitleId(String titleno) {
        CartItemsAndImagesList.titleID.remove(new String(titleno));
    }


}
