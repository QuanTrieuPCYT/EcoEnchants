package com.willfp.ecoenchants.proxy.v1_17_R1

import com.willfp.ecoenchants.proxy.proxies.EcoCraftEnchantmentManagerProxy
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment

class EcoCraftEnchantmentManager : EcoCraftEnchantmentManagerProxy {
    override fun registerNewCraftEnchantment(
        enchantment: Enchantment,
        maxLevel: Int,
        conflicts: Collection<NamespacedKey>
    ) {
        for (enchant in net.minecraft.core.IRegistry.X) {
            val key = org.bukkit.craftbukkit.v1_17_R1.util.CraftNamespacedKey.fromMinecraft(
                net.minecraft.core.IRegistry.X.getKey(enchant)
            )
            if (key.key != enchantment.key.key) {
                continue
            }
            EcoCraftEnchantment(enchant, maxLevel, conflicts).register()
        }
    }
}
