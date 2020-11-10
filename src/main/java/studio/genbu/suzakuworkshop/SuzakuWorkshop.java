package studio.genbu.suzakuworkshop;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import studio.genbu.suzakuworkshop.Commands.GetCustomItem;
import studio.genbu.suzakuworkshop.Events.InventoryEvents;
import studio.genbu.suzakuworkshop.Items.CustomItem;
import studio.genbu.suzakuworkshop.Manager.CustomItemsManager;

public class SuzakuWorkshop extends JavaPlugin {

  private static SuzakuWorkshop suzakuWorkshop;
  private static CustomItemsManager customItemsManager;
  
  /**
   * プラグインを有効化した際に呼び出すメソッド。
   */
  @Override
  public void onEnable() {
    suzakuWorkshop = this;
    customItemsManager = CustomItemsManager.getInstance();

    CustomItem customItem = new CustomItem(
      "testitem",
      Material.DIAMOND,
      "テストアイテム",
      new String[] {"どうやら、このアイテムには", "未来と希望が詰まっているらしい"},
      0
    );

    customItemsManager.addCustomItem(customItem);

    getCommand("getcustomitem").setExecutor(new GetCustomItem());

    getServer().getPluginManager().registerEvents(new InventoryEvents(), this);

    getLogger().info("SuzakuWorkshop を有効化しました。");
  }

  /**
   * プラグインを無効化した際に呼び出すメソッド。
   */
  @Override
  public void onDisable() {
    getLogger().info("SuzakuWorkshop を無効化しました。");
  }

  /**
   * SuzakuWorkshop クラスのインスタンスを返すメソッド。
   * @return SuzakuWorkshop クラスのインスタンス
   */
  public static SuzakuWorkshop getInstance() {
    return suzakuWorkshop;
  }

}
