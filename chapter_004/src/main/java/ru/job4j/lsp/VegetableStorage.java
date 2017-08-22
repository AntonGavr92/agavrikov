package ru.job4j.lsp;

/**
 * Created by gavrikov.a on 22/08/2017.
 */
public class VegetableStorage extends Storage {

    /**
     * Constructor for initialisation fields.
     *
     * @param percentExpiryControlFrom lower limit of the expiry in percent.
     * @param percentExpiryControlTo   upper limit of the expiry in percent.
     */
    public VegetableStorage(int percentExpiryControlFrom, int percentExpiryControlTo) {
        super(percentExpiryControlFrom, percentExpiryControlTo);
    }
}
