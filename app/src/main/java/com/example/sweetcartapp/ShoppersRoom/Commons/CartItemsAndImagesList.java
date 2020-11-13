package com.example.sweetcartapp.ShoppersRoom.Commons;

import java.util.LinkedList;
import java.util.List;

// Using LinkedList shows the beauty of Data Structures in
// adding and removing items in list with worst Case Complexity O(1)
// -most efficient for List Items.

public class CartItemsAndImagesList {
    public static List<Integer> imageId = new LinkedList<>();
    public static List<String> titleID = new LinkedList<>();

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
