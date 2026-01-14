package me.VoltMC.VoltSMP.Functions;

import me.VoltMC.VoltSMP.Main;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class givePlayerRank {

    public static void removePlayerRank(Player p, String rank) {
        UUID pUUID = p.getUniqueId();
        CompletableFuture<User> userFuture = Main.getInstance().luckAPI().getUserManager().loadUser(pUUID);
        User user = userFuture.join();

        Node pRank = Node.builder("group." + rank).build();
        user.data().remove(pRank);
        Main.getInstance().luckAPI().getUserManager().saveUser(user);
    }

    public static void setPLayerRank(Player p, String rank) {
        UUID pUUID = p.getUniqueId();
        CompletableFuture<User> userFuture = Main.getInstance().luckAPI().getUserManager().loadUser(pUUID);
        User user = userFuture.join();

        Node pRank = Node.builder("group." + rank).build();
        user.data().add(pRank);
        Main.getInstance().luckAPI().getUserManager().saveUser(user);
    }



}
