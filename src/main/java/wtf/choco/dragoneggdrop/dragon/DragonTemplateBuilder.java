package wtf.choco.dragoneggdrop.dragon;

import com.google.common.base.Preconditions;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wtf.choco.dragoneggdrop.dragon.loot.DragonLootTable;
import wtf.choco.dragoneggdrop.particle.ParticleShapeDefinition;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A builder class for {@link DragonTemplate} instances.
 *
 * @author Parker Hawke - Choco
 *
 * @see DragonTemplate#builder(String)
 * @see DragonTemplate#buildCopy(String, DragonTemplate)
 */
public final class DragonTemplateBuilder {

    private ParticleShapeDefinition particleShapeDefinition;
    private DragonLootTable lootTable;
    private double spawnWeight;
    private String name;
    private BarStyle barStyle;
    private BarColor barColour;
    private List<@NotNull String> spawnAnnouncement;
    private Map<@NotNull Attribute, @NotNull Double> attributes;

    private final String id;

    DragonTemplateBuilder(@NotNull String id) {
        Preconditions.checkArgument(id != null && !id.isEmpty(), "id must not be empty or null");
        Preconditions.checkArgument(!id.contains(" "), "id must not have any spaces");

        this.id = id;
    }

    DragonTemplateBuilder(@NotNull String id, @NotNull DragonTemplate template) {
        this(id);

        Preconditions.checkArgument(template != null, "template must not be null");

        this.particleShapeDefinition = template.getParticleShapeDefinition();
        this.lootTable = template.getLootTable();
        this.spawnWeight = template.getSpawnWeight();
        this.name = template.getName();
        this.barStyle = template.getBarStyle();
        this.barColour = template.getBarColor();
        this.spawnAnnouncement = template.getSpawnAnnouncement();
        this.attributes = template.getAttributes();
    }

    /**
     * Set the {@link ParticleShapeDefinition}.
     *
     * @param particleShapeDefinition the particle shape definition to set
     *
     * @return this instance. Allows for chained method calls
     */
    @NotNull
    public DragonTemplateBuilder particleShapeDefinition(@Nullable ParticleShapeDefinition particleShapeDefinition) {
        this.particleShapeDefinition = particleShapeDefinition;
        return this;
    }

    /**
     * Set the {@link DragonLootTable}.
     *
     * @param lootTable the loot table to set
     *
     * @return this instance. Allows for chained method calls
     */
    @NotNull
    public DragonTemplateBuilder lootTable(@Nullable DragonLootTable lootTable) {
        this.lootTable = lootTable;
        return this;
    }

    /**
     * Set the spawn weight.
     *
     * @param spawnWeight the spawn weight to set
     *
     * @return this instance. Allows for chained method calls
     */
    @NotNull
    public DragonTemplateBuilder spawnWeight(double spawnWeight) {
        this.spawnWeight = spawnWeight;
        return this;
    }

    /**
     * Set the name.
     *
     * @param name the name to set
     *
     * @return this instance. Allows for chained method calls
     */
    @NotNull
    public DragonTemplateBuilder name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * Set the boss bar style.
     *
     * @param barStyle the bar style to set
     *
     * @return this instance. Allows for chained method calls
     */
    @NotNull
    public DragonTemplateBuilder barStyle(@Nullable BarStyle barStyle) {
        this.barStyle = barStyle;
        return this;
    }

    /**
     * Set the boss bar colour.
     *
     * @param barColour the colour to set
     *
     * @return this instance. Allows for chained method calls
     */
    @NotNull
    public DragonTemplateBuilder barColor(@Nullable BarColor barColour) {
        this.barColour = barColour;
        return this;
    }

    /**
     * Set the spawn announcement.
     *
     * @param spawnAnnouncement the spawn announcement to set
     *
     * @return this instance. Allows for chained method calls
     */
    @NotNull
    public DragonTemplateBuilder spawnAnnouncement(@Nullable List<@NotNull String> spawnAnnouncement) {
        this.spawnAnnouncement = spawnAnnouncement;
        return this;
    }

    /**
     * Add an attribute and apply to it the given base value.
     *
     * @param attribute the attribute to set
     * @param value the base value to set
     *
     * @return this instance. Allows for chained method calls
     */
    @NotNull
    public DragonTemplateBuilder attribute(@NotNull Attribute attribute, double value) {
        if (attributes == null) {
            this.attributes = new HashMap<>();
        }

        this.attributes.put(attribute, value);
        return this;
    }

    /**
     * Build the final {@link DragonTemplate} instance.
     *
     * @return the dragon template
     */
    @NotNull
    public DragonTemplate build() {
        return new DragonTemplate(id, particleShapeDefinition, lootTable, spawnWeight, name, barStyle, barColour, spawnAnnouncement, attributes);
    }

}
