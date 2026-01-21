package me.VoltMC.VoltSMP.Functions;

import me.VoltMC.VoltSMP.Main;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class givePlayerRank {

    public static void addPermission(Player p, String permission) {
        UUID pUUID = p.getUniqueId();
        Main.getInstance().luckAPI().getUserManager().modifyUser(pUUID, user -> {
            Node permissionNode = Node.builder(permission).build();
            user.data().add(permissionNode);
        });
    }

    public static void removePlayerRank(Player p, String rank) {
        UUID pUUID = p.getUniqueId();
        CompletableFuture<User> userFuture = Main.getInstance().luckAPI().getUserManager().loadUser(pUUID);
        User user = userFuture.join();

        Node pRank = Node.builder("group." + rank).build();
        user.data().remove(pRank);
        Main.getInstance().luckAPI().getUserManager().saveUser(user);
    }

    public static void setPlayerRank(Player p, String rank) {
        UUID pUUID = p.getUniqueId();
        LuckPerms lp = Main.getInstance().luckAPI();

        System.out.println("Running set rank");

        if (rank == null || rank.isEmpty()) {
            System.out.println(rank + " does not exist, please create the group!");
            return;
        }

        lp.getUserManager().loadUser(pUUID).thenAccept(user -> {
            user.data().clear(node -> node instanceof InheritanceNode);
            user.data().add(InheritanceNode.builder(rank).build());
            user.data().clear(node ->
                    node.getKey().equals("primary-group")
            );
            user.data().add(
                    net.luckperms.api.node.types.MetaNode.builder("primary-group", rank).build()
            );

            lp.getUserManager().saveUser(user);
        });
    }



}
