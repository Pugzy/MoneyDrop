MoneyDrop v1.0.0
===========

Plugin created in response to a "plugin request" on the Bukkit forums hence the limited customisation options.

When a player dies items listed in the `config.yml` file will be dropped by the player. Items are removed and dropped naturally from the players inventory, if you wish for the player to keep the remaining inventory then the gamerule `keepInventory` must be set to true.


### Commands and Permissions

Only one command exists at the moment, this is a debug command that toggles debug mode. When debug mode is active the material types and counts are output to the server (can later be used to display to the user what they've lost due to the death).

```
commands:
  moneydrop:
    description: "Toggles debug mode."
    usage: "/moneydrop debug"
permissions:
  money.drop.debug:
      description: Enable debug printing.
      default: op
  money.drop:
    description: Player will drop money.
    default: true
  money.drop.exempt:
     description: Player will not drop money.
     default: false
```

### Default Config

Like the comment states items can be added or removed from this list.

```
# Items that will drop
drops:
 - GOLD_NUGGET
 - GOLD_INGOT
 - GOLD_BLOCK
 # More can be added here.. etc
 # - EMERALD
```

### Download / Compile

The plugin was built in Intelij IDEA using Maven (`pom.xml`) included in Git repo.
