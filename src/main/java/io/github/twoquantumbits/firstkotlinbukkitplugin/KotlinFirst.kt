package io.github.twoquantumbits.firstkotlinbukkitplugin

import org.bukkit.attribute.Attribute
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import kotlin.math.min

class EventListener : Listener {

    @EventHandler
    fun onPlayerSneak(event: PlayerToggleSneakEvent) {
        // recreating the first thing I did back when I started coding plugins: "you sneak you revive"
        if(event.isSneaking) {
            event.player.health =
                min(event.player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue, event.player.health + 1)
        }
    }

    // bonus: when player eats golden apple, they get Levitation 1 for 10 seconds - thanks Copilot!
    @EventHandler
    fun onPlayerEatGoldenApple(event: PlayerItemConsumeEvent) {
        if(event.item.type == org.bukkit.Material.GOLDEN_APPLE) {
            event.player.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, 10 * 20, 0))}}

    // concept: if A gets damaged by B, B gets damaged by (damage caused to A * inverse of rate of health A still has
    // will be fixed later, I'll leave it here for now
//    @EventHandler
//    fun finalBlow(event: EntityDamageByEntityEvent) {
//        if(event.entity.type == org.bukkit.entity.EntityType.PLAYER) {
//            if(event.damager is LivingEntity) (event.damager as LivingEntity).health -= event.damage * (1F / (event.entity as LivingEntity).health)
//        }
//    }
}

class KotlinFirst : JavaPlugin() {
    override fun onEnable() {
        println("KotlinFirst enabled!")
        server.pluginManager.registerEvents(EventListener(), this)
    }

    override fun onDisable() {
        println("KotlinFirst disabled!")
    }
}