package com.ulxsth.police_wanted.usecase;

import com.ulxsth.police_wanted.model.item.GameItem;
import com.ulxsth.police_wanted.util.ItemConfig;
import org.bukkit.inventory.ItemStack;

public class CreateItemStackUseCase {
    public static ItemStack execute(String id, int amount) throws IllegalArgumentException {
        GameItem item = ItemConfig.getItemById(id);
        return item.createItemStack(amount);
    }
}
