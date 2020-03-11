package com.ninjaguild.dragoneggdrop.dragon.loot.pool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.ninjaguild.dragoneggdrop.dragon.loot.elements.DragonLootElementItem;
import com.ninjaguild.dragoneggdrop.utils.math.MathUtils;

public class LootPoolItem extends AbstractLootPool<DragonLootElementItem> {

    public LootPoolItem(String name, double chance, int minRolls, int maxRolls, Collection<DragonLootElementItem> itemElements) {
        super(name, chance, minRolls, maxRolls, itemElements);
    }

    public LootPoolItem(String name, double chance, int rolls, Collection<DragonLootElementItem> itemElements) {
        this(name, chance, rolls, rolls, itemElements);
    }

    public LootPoolItem(String name, int minRolls, int maxRolls, Collection<DragonLootElementItem> itemElements) {
        this(name, 100.0, minRolls, maxRolls, itemElements);
    }

    public LootPoolItem(String name, int rolls, Collection<DragonLootElementItem> itemElements) {
        this(name, 100.0, rolls, rolls, itemElements);
    }

    @Override
    public JsonObject toJson() {
        return new JsonObject(); // TODO: Write to JSON
    }

    public static LootPoolItem fromJson(JsonObject root) {
        int minRolls = 0, maxRolls = 0;
        String name = root.has("name") ? root.get("name").getAsString() : null;
        double chance = root.has("chance") ? MathUtils.clamp(root.get("chance").getAsDouble(), 0.0, 100.0) : 100.0;
        List<DragonLootElementItem> itemElements = new ArrayList<>();

        if (root.has("rolls")) {
            JsonElement rolls = root.get("rolls");
            if (rolls.isJsonPrimitive()) {
                minRolls = maxRolls = Math.max(rolls.getAsInt(), 0);
            }

            else if (rolls.isJsonObject()) {
                JsonObject rollsObject = rolls.getAsJsonObject();
                minRolls = rollsObject.has("min") ? Math.max(rollsObject.get("min").getAsInt(), 0) : 0;
                maxRolls = rollsObject.has("max") ? Math.max(rollsObject.get("max").getAsInt(), 0) : minRolls;
            }
        }

        if (!root.has("items") || !root.get("items").isJsonArray()) {
            throw new JsonParseException("Missing \"items\" array for loot pool with name " + name);
        }

        JsonArray itemsArray = root.getAsJsonArray("items");
        for (JsonElement element : itemsArray) {
            if (!element.isJsonObject()) {
                throw new JsonParseException("Invalid item for loot pool with name " + name + ". Expected object. Got " + element.getClass().getSimpleName());
            }

            itemElements.add(DragonLootElementItem.fromJson(element.getAsJsonObject()));
        }

        return new LootPoolItem(name, chance, minRolls, maxRolls, itemElements);
    }

}
