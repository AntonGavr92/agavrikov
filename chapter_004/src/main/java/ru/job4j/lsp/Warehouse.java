package ru.job4j.lsp;

/**
 * Warehouse class.
 * @author agavrikov
 * @since 22.08.2017
 * @version 1
 */
public class Warehouse extends Storage {

    /**
     * Constructor for initialisation fields.
     * @param percentExpiryControlFrom lower limit of the expiry in percent.
     * @param percentExpiryControlTo upper limit of the expiry in percent.
     */
    public Warehouse(int percentExpiryControlFrom, int percentExpiryControlTo) {
        super(percentExpiryControlFrom, percentExpiryControlTo);
    }

}
