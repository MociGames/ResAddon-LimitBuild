package cn.moci.share.limitbuild;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.ResidencePermissions;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.entity.EntityInteractEvent;

public class Listeners implements Listener {

		private boolean hasPerm(Location loc, String name) {
				ClaimedResidence res = Residence.getInstance().getResidenceManager().getByLoc(loc);
				return res != null ? res.getPermissions().playerHas(name, "build", true) || res.getPermissions().playerHas(name, "trusted", true) : false;
		}

		@EventHandler
		public void onBlockBreak(BlockBreakEvent e) {
				if (e.isCancelled() || e.getPlayer().hasPermission(Main.perm) || e.getPlayer().isOp() && Main.wolrd.contains(e.getPlayer().getWorld().getName()) && Main.blockBreak) {
						return;
				}
				boolean hasPerm = hasPerm(e.getBlock().getLocation(), e.getPlayer().getName());
				e.setCancelled(!hasPerm);
				if (!hasPerm) {
						e.getPlayer().sendMessage(Main.msg);
				}
		}

		@EventHandler
		public void onBlockPlace(BlockPlaceEvent e) {
				if (e.isCancelled() || e.getPlayer().hasPermission(Main.perm) || e.getPlayer().isOp() && Main.wolrd.contains(e.getPlayer().getWorld().getName()) && Main.blockPlace) {
						return;
				}
				boolean hasPerm = hasPerm(e.getBlock().getLocation(), e.getPlayer().getName());
				e.setCancelled(!hasPerm);
				if (!hasPerm) {
						e.getPlayer().sendMessage(Main.msg);
				}
		}

		@EventHandler
		public void onEntityDamage(EntityDamageByEntityEvent e) {
				if (e.getEntity() instanceof Monster && !Main.monsters) {
						return;
				}
				if (Main.others) {
						if (e.getDamager() instanceof Player) {
								Player p = (Player) e.getDamager();
								if (e.isCancelled() || p.hasPermission(Main.perm) || p.isOp() && Main.wolrd.contains(p.getWorld().getName())) {
										return;
								}
								boolean hasPerm = hasPerm(e.getEntity().getLocation(), p.getName());
								e.setCancelled(!hasPerm);
								if (!hasPerm) {
										p.sendMessage(Main.msg);
								}
						} else if ((e.getDamager() instanceof Arrow)) {
								Arrow a = ((Arrow) e.getDamager());
								if (a.getShooter() instanceof Player) {
										Player p = (Player) a.getShooter();
										if (e.isCancelled() || p.hasPermission(Main.perm) || p.isOp() && Main.wolrd.contains(p.getWorld().getName())) {
												return;
										}
										boolean hasPerm = hasPerm(e.getEntity().getLocation(), p.getName());
										e.setCancelled(!hasPerm);
										if (!hasPerm) {
												p.sendMessage(Main.msg);
										}
								}
						} else if (e.getDamager() instanceof Snowball) {
								Snowball s = (Snowball) e.getDamager();
								if (s.getShooter() instanceof Player) {
										Player p = (Player) s.getShooter();
										if (e.isCancelled() || p.hasPermission(Main.perm) || p.isOp() && Main.wolrd.contains(p.getWorld().getName())) {
												return;
										}
										if (e.isCancelled() || p.hasPermission(Main.perm) || p.isOp() && Main.wolrd.contains(p.getWorld().getName())) {
												return;
										}
										boolean hasPerm = hasPerm(e.getEntity().getLocation(), p.getName());
										e.setCancelled(!hasPerm);
										if (!hasPerm) {
												p.sendMessage(Main.msg);
										}
								}

						}
				}

		}

		@EventHandler
		public void soilChangePlayer(PlayerInteractEvent e) {
				if (e.isCancelled() || e.getPlayer().hasPermission(Main.perm) || e.getPlayer().isOp() && Main.wolrd.contains(e.getPlayer().getWorld().getName()) && Main.pi) {
						return;
				}
				if (((e.getAction() == Action.PHYSICAL) && (e.getClickedBlock().getType() == Material.SOIL))
						|| (e.getAction() == Action.LEFT_CLICK_BLOCK)
						|| (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
						boolean hasPerm = hasPerm(e.getClickedBlock().getLocation(), e.getPlayer().getName());
						e.setCancelled(!hasPerm);
						if (!hasPerm) {
								e.getPlayer().sendMessage(Main.msg);
						}
				}
		}

		@EventHandler
		public void soilChangeAnimal(EntityInteractEvent e) {
				if (Main.ei) {
						e.setCancelled(true);
				}

		}

		@EventHandler
		public void onBukkitFill(PlayerBucketFillEvent e) {
				if (e.isCancelled() || e.getPlayer().hasPermission(Main.perm) || e.getPlayer().isOp() && Main.wolrd.contains(e.getPlayer().getWorld().getName()) && Main.pbf) {
						return;
				}
				boolean hasPerm = hasPerm(e.getBlockClicked().getLocation(), e.getPlayer().getName());
				e.setCancelled(!hasPerm);
				if (!hasPerm) {
						e.getPlayer().sendMessage(Main.msg);
				}

		}

		@EventHandler
		public void onEmptyl(PlayerBucketEmptyEvent e) {
				if (e.isCancelled() || e.getPlayer().hasPermission(Main.perm) || e.getPlayer().isOp() && Main.wolrd.contains(e.getPlayer().getWorld().getName()) && Main.pbe) {
						return;
				}
				boolean hasPerm = hasPerm(e.getBlockClicked().getLocation(), e.getPlayer().getName());
				e.setCancelled(!hasPerm);
				if (!hasPerm) {
						e.getPlayer().sendMessage(Main.msg);
				}
		}

}
