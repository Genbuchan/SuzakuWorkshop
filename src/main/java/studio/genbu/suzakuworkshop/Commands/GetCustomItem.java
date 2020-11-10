package studio.genbu.suzakuworkshop.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import studio.genbu.suzakuworkshop.Manager.CustomItemsManager;

public class GetCustomItem implements CommandExecutor {

  private CustomItemsManager customItemsManager;

  /**
   * GetCustomItem のコンストラクタ。
   */
  public GetCustomItem() {
    this.customItemsManager = CustomItemsManager.getInstance();
  }

  /**
   * コマンドが実行された際の処理。
   * @param sender コマンドの実行者
   * @param command コマンド
   * @param label コマンドの別名
   * @param args 引数
   */
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    Player player = (Player)sender;

    if(args.length != 0) {

      String customItemId = args[0];

      if(customItemsManager.hasCustomItemFromItemID(customItemId)) {
        Inventory inventory = player.getInventory();
        inventory.addItem(customItemsManager.getCustomItem(customItemId).getItemStack());
        player.updateInventory();
        return true;
      } else {
        player.sendMessage("そのカスタムアイテムは存在しません。");
      }

    } else {
      player.sendMessage("引数がありません。");
    }

    return false;

  }

}
