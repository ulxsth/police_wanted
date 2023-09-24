package com.ulxsth.police_wanted.model.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameItem {
    private final Material material;
    private final String name;
    private final int id;

    public GameItem(String materialId, String name, int id) {
        // IDからマテリアルを検索
        Material material = Material.getMaterial(materialId);
        if(material == null) {
            throw new IllegalArgumentException("materialId is invalid");
        }

        this.material = material;
        this.name = name;
        this.id = id;
    }

    public GameItem(Material material, String name, int id) {
        this.material = material;
        this.name = name;
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    /**
     * ItemStackを生成する
     * @param amount
     * @return ItemStack
     */
    public ItemStack createItemStack(int amount) {
        ItemStack itemStack = new ItemStack(this.material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.text(this.name));
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
