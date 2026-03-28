package br.furb.problema03.facades.blinds;

import br.furb.analise.algoritmos.PersianaNatLight;
import br.furb.analise.algoritmos.PersianaSolarius;

public class IntelligentBlindsFacadee {
    private final PersianaNatLight persianaNatLight;
    private final PersianaSolarius persianaSolarius;

    public IntelligentBlindsFacadee(PersianaNatLight persianaNatLight, PersianaSolarius persianaSolarius) {
        this.persianaNatLight = persianaNatLight;
        this.persianaSolarius = persianaSolarius;
    }

    public void open() throws Exception {
        persianaSolarius.subirPersiana();
        try{
            persianaNatLight.subirPalheta();
        }catch (Exception e){
            openNatLightBlade();
            persianaNatLight.subirPalheta();
        }
    }

    public void close() throws Exception {
        persianaSolarius.descerPersiana();
        if (persianaNatLight.estaPalhetaErguida()) {
            persianaNatLight.descerPalheta();
        }

        if (persianaNatLight.estaPalhetaAberta()) {
            persianaNatLight.fecharPalheta();
        }
    }

    public void openNatLightBlade(){
        persianaNatLight.abrirPalheta();
    }

    public void closeNatLightBlade() throws Exception {
        try {
            persianaNatLight.fecharPalheta();
        } catch (Exception e) {
            persianaNatLight.descerPalheta();
            persianaNatLight.fecharPalheta();
        }
    }
}
