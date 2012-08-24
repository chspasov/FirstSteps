package com.wallet.application;

/**
 * Author: TsvetanSpasov
 * @version 1.00
 */
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.wallet.country.CountriesService;

@ApplicationPath("/")
public class WalletApplication extends Application {

    public WalletApplication() {
        /**
         * initialize CountriesService as put in the HashMap key and value
         */
        CountriesService.init();
    }

}
