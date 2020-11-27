package com.babbangona.hyperlogger;

import android.content.Context;

import com.babbangona.hyperlogger.Database.DatabaseStringConstants;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

public class MixPanelActivator {

    Context context;

    MixpanelAPI mix_panel;

    public MixPanelActivator(Context context, boolean activate) {
        this.context = context;
        if(activate){
            mix_panel = MixpanelAPI.getInstance(context, DatabaseStringConstants.MIX_PANEL_TOKEN);
        }
    }

    public void deactivateMixPanel(){
        mix_panel.optOutTracking();
    }

    public boolean checkMixPanelExitStatus(){
        return mix_panel.hasOptedOutTracking();
    }

}
