package studio.genbu.suzakuworkshop.Events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import studio.genbu.suzakuworkshop.SuzakuWorkshop;
import studio.genbu.suzakuworkshop.Items.CustomItem;
import studio.genbu.suzakuworkshop.Manager.CustomItemsManager;

public class InventoryEvents implements Listener {

  SuzakuWorkshop main = SuzakuWorkshop.getInstance();
  CustomItemsManager manager = CustomItemsManager.getInstance();


  /**
   * インベントリをクリックした際に動作するイベント
   * @param event InventoryClickEvent
   */
  @EventHandler
  public void onInventoryClickEvent(InventoryClickEvent event) {

    Player player = (Player) event.getWhoClicked();

    if(player.getGameMode() != GameMode.CREATIVE) {

      ItemStack itemStack = event.getCursor();

      if (itemStack.getType() != Material.AIR) {

        ItemStack updatedItemStack = updateCustomItemStack(itemStack);

        if(updatedItemStack != null) {
          new BukkitRunnable() {

            @Override
            public void run() {
              event.setCurrentItem(updatedItemStack);
            }

          }.runTask(main);
        }
      }
    }
  }

  /**
   * クリエイティブモードでインベントリをクリックした際に動作するイベント
   * @param event InventoryCreativeEvent
   */
  @EventHandler
  public void onInventoryClickEvent(InventoryCreativeEvent event) {

    ItemStack itemStack = event.getCursor();

    if (itemStack.getType() != Material.AIR) {

      ItemStack updatedItemStack = updateCustomItemStack(itemStack);

      if(updatedItemStack != null) {
        event.setCursor(updatedItemStack);
      }

    }
  }

  /**
   * アイテムをアップデートするメソッド
   * @param itemStack ItemStack
   */
  private ItemStack updateCustomItemStack(ItemStack itemStack) {
    ItemMeta itemMeta = itemStack.getItemMeta();
    NamespacedKey customItemIdKey = new NamespacedKey(main, "customItemId");

    PersistentDataContainer container = itemMeta.getPersistentDataContainer();

    if (container.has(customItemIdKey, PersistentDataType.STRING)) {
      String customItemId = container.get(customItemIdKey, PersistentDataType.STRING);

      if (manager.hasCustomItemFromItemID(customItemId)) {
        CustomItem customItem = manager.getCustomItem(customItemId);
        ItemStack customItemStack = customItem.getItemStack();

        ItemStack compareItemStack = itemStack.clone();
        compareItemStack.setAmount(1);

        if (!compareItemStack.equals(customItemStack)) {
          ItemStack convertedCustomItemStack = customItemStack.clone();
          convertedCustomItemStack.setAmount(itemStack.getAmount());

          return convertedCustomItemStack;
        }

      }

    }

    return null;
  }

}
