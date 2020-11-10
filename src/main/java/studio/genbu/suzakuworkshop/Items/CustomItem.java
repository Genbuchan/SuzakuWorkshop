package studio.genbu.suzakuworkshop.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import studio.genbu.suzakuworkshop.SuzakuWorkshop;

public class CustomItem {

  private SuzakuWorkshop main = SuzakuWorkshop.getInstance();
  
  private String customItemId;
  private Material material;
  private String displayName;
  private String[] lore;
  private int customModelData;

  private ItemStack itemStack;

  /**
   * CustomItem のコンストラクタ。
   * @param customItemId カスタムアイテムの ID
   * @param material 素材
   * @param displayName 表示名
   * @param lore 説明
   * @param customModelData カスタムモデルの ID
   */
  public CustomItem(
    String customItemId,
    Material material,
    String displayName,
    String[] lore,
    int customModelData
  ) {
      this.customItemId = customItemId;
      this.material = material;
      this.displayName = displayName;
      this.lore = lore;
      this.customModelData = customModelData;

      this.itemStack = generateItemStack();
  }

  /**
   * カスタムアイテムの ID を取得するメソッド。
   * @return カスタムアイテムの ID
   */
  public String getCustomItemId() {
    return this.customItemId;
  }

  /**
   * カスタムアイテムの素材を取得するメソッド。
   * @return Material
   */
  public Material getMaterial() {
    return this.material;
  }

  /**
   * カスタムアイテムの表示名を取得するメソッド。
   * @return String
   */
  public String getDisplayName() {
    return this.displayName;
  }

  /**
   * カスタムアイテムの説明を取得するメソッド。
   * @return String[]
   */
  public String[] getLore() {
    return this.lore;
  }

  /**
   * カスタムアイテムのカスタムモデルの ID を取得するメソッド。
   * @return カスタムモデルの ID
   */
  public int getCustomModelData() {
    return this.customModelData;
  }

  /**
   * カスタムアイテムの ItemStack を取得するメソッド。
   * @return ItemStack
   */
  public ItemStack getItemStack() {
    return this.itemStack;
  }

  /**
   * ItemStack を生成
   * @return ItemStack
   */
  private ItemStack generateItemStack() {
    itemStack = new ItemStack(this.material);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName(ChatColor.RESET + this.displayName);

    List<String> lore = new ArrayList<String>();
    Arrays.asList(this.lore).forEach(string -> {
      lore.add(ChatColor.RESET.toString() + ChatColor.WHITE.toString() + string);
    });

    itemMeta.setLore(lore);
    itemMeta.setCustomModelData(this.customModelData);

    PersistentDataContainer container = itemMeta.getPersistentDataContainer();
    container.set(new NamespacedKey(main, "customItemId"), PersistentDataType.STRING, this.customItemId);

    itemStack.setItemMeta(itemMeta);

    return itemStack;
  }

}
