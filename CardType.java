public enum CardType {
    EXPLODE("explode"),
    DEFUSE("defuse"),
    NOPE("nope"),
    ATTACK("attack"),
    SKIP("SKIP"),
    FAVOR("favor"),
    SHUFFLE("shuffle"),
    SEE_THE_FUTURE("future"),
    CAT_WATERMELON("cat_watermelon"),
    CAT_RAINBOW("cat_rainbow"),
    CAT_BEARD("cat_beard"),
    CAT_TACO("cat_taco"),
    CAT_POTATO("cat potato");

    private final String formatName;

    CardType(String formatName) {
        this.formatName = formatName;
    }

    public String getFormatName() {
        return this.formatName;
    }
}
