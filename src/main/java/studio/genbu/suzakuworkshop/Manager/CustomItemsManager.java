package studio.genbu.suzakuworkshop.Manager;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import studio.genbu.suzakuworkshop.SuzakuWorkshop;
import studio.genbu.suzakuworkshop.Items.CustomItem;

public class CustomItemsManager {
  
  private static SuzakuWorkshop main = SuzakuWorkshop.getInstance();

  private static final CustomItemsManager customItemsManager = new CustomItemsManager();
  private static ConcurrentHashMap<String, CustomItem> customItems = new ConcurrentHashMap<String, CustomItem>();

  private CustomItemsManager() {

  }

  /**
   * カスタムアイテムを追加するメソッド。
   * @param customItem カスタムアイテム
   */
  public void addCustomItem(CustomItem customItem) {
    customItems.putIfAbsent(
      customItem.getCustomItemId(),
      customItem
    );
  }

  /**
   * カスタムアイテムを取得するメソッド。
   * @param customItemId カスタムアイテムの ID
   * @return カスタムアイテム
   */
  public CustomItem getCustomItem(String customItemId) {
    if(customItems.containsKey(customItemId)) {
      return customItems.get(customItemId);
    } else {
      return null;
    }
  }

  /**
   * CustomItemsManager のインスタンスを取得するメソッド。
   * @return CustomItemsManager
   */
  public static CustomItemsManager getInstance() {
    return customItemsManager;
  }

  /**
   * 特定の ID のカスタムアイテムがマネージャーに存在するか確認するメソッド。
   * @param customItemId カスタムアイテムの ID
   * @return boolean
   */
  public boolean hasCustomItemFromItemID(String customItemId) {
    if(customItems.containsKey(customItemId)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * ItemStack に保存されたカスタムアイテムがマネージャーに存在するか確認するメソッド。
   * @param itemStack ItemStack
   * @return boolean
   */
  public boolean hasCustomItemFromItemStack(ItemStack itemStack) {

    PersistentDataContainer container = itemStack.getItemMeta().getPersistentDataContainer();
    NamespacedKey customItemIdKey = new NamespacedKey(main, "customItemId");

    if(container.has(customItemIdKey, PersistentDataType.STRING)) {
      String customItemId = container.get(customItemIdKey, PersistentDataType.STRING);
      if(customItems.containsKey(customItemId)) {
        return true;
      }
    }
    return false;

  }
}