/*
 * This file is a part of project QuickShop, the name is InternalListener.java
 *  Copyright (C) Ghost_chu <https://github.com/Ghost-chu>
 *  Copyright (C) PotatoCraft Studio and contributors
 *
 *  This program is free software: you can redistribute it and/or modify it
 *  under the terms of the GNU Lesser General Public License as published by the
 *  Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT
 *  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 *  FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 *  for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.maxgamer.quickshop.builtinlistener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.event.*;
import org.maxgamer.quickshop.shop.ShopType;
import org.maxgamer.quickshop.util.Util;


public class InternalListener implements Listener {
    private final QuickShop plugin;
    private final boolean loggingBalance;
    private final boolean loggingAction;

    public InternalListener(QuickShop plugin) {
        this.plugin = plugin;
        this.loggingBalance = plugin.getConfig().getBoolean("logging.log-balance");
        this.loggingAction = plugin.getConfig().getBoolean("logging.log-actions");
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void shopCreate(ShopCreateEvent event) {
        if (loggingAction) {
            plugin.log(
                    "Player "
                            + event.getPlayer().getName()
                            + " created a shop at location "
                            + event.getShop().getLocation());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void shopDelete(ShopDeleteEvent event) {
        if (loggingAction) {
            plugin.log("Shop at " + event.getShop().getLocation() + " was removed.");
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void shopModeratorChanges(ShopModeratorChangedEvent event) {
        if (loggingAction) {
            plugin.log(
                    "Shop at location "
                            + event.getShop().getLocation()
                            + " moderator was changed to "
                            + event.getModerator());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void shopPriceChanges(ShopPriceChangeEvent event) {
        if (loggingAction) {
            plugin.log(
                    "Shop at location "
                            + event.getShop().getLocation()
                            + " price was changed from "
                            + event.getOldPrice()
                            + " to "
                            + event.getNewPrice());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void shopPrePurchase(ShopPurchaseEvent event) {
        if (loggingBalance) {
            plugin.log("Player " + event.getPlayer().getName() + " had " + plugin.getEconomy().getBalance(event.getPlayer().getUniqueId()) + " before trading.");
            plugin.log("Shop Owner " + event.getShop().ownerName() + " had " + plugin.getEconomy().getBalance(event.getShop().getOwner()) + " before trading.");
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void shopPurchase(ShopSuccessPurchaseEvent event) {
        if (loggingAction) {
            if (event.getShop().getShopType() == ShopType.BUYING) {
                plugin.log(
                        "Player "
                                + event.getPlayer().getName()
                                + " sold "
                                + event.getShop().ownerName()
                                + " shop "
                                + event.getShop()
                                + " for"
                                + Util.getItemStackName(event.getShop().getItem())
                                + "x" +
                                +event.getAmount()
                                + " for "
                                + event.getBalance()
                                + " ("
                                + event.getTax()
                                + " tax).");
            }
            if (event.getShop().getShopType() == ShopType.SELLING) {
                plugin.log(
                        "Player "
                                + event.getPlayer().getName()
                                + " bought "
                                + event.getShop().ownerName()
                                + " shop "
                                + event.getShop()
                                + " for "
                                + Util.getItemStackName(event.getShop().getItem())
                                + " x"
                                + event.getAmount()
                                + " for "
                                + event.getBalance()
                                + " ("
                                + event.getTax()
                                + " tax).");

            }
        }
        if (loggingBalance) {
            plugin.log("Player " + event.getPlayer().getName() + " had " + plugin.getEconomy().getBalance(event.getPlayer().getUniqueId()) + " after trading.");
            plugin.log("Shop Owner " + event.getShop().ownerName() + " had " + plugin.getEconomy().getBalance(event.getShop().getOwner()) + " after trading.");
        }
    }

}
