package br.furb.problema03.facades.blinds;

import br.furb.analise.algoritmos.PersianaNatLight;

public class PersianaNatLightStrategy implements BlindsStrategy {
    private final PersianaNatLight blinds;

    public PersianaNatLightStrategy(PersianaNatLight blinds) {
        this.blinds = blinds;
    }

    @Override
    public void open() throws Exception {
        // if (!blinds.estaPalhetaAberta()) {
        //     blinds.abrirPalheta();
        // }

        // if (!blinds.estaPalhetaErguida()) {
        //     blinds.subirPalheta();
        // }
        try{
            blinds.subirPalheta();
        }catch (Exception e){
            blinds.abrirPalheta();
            blinds.subirPalheta();
        }
    }

    @Override
    public void close() throws Exception {
        // if (blinds.estaPalhetaErguida()) {
        //     blinds.descerPalheta();
        // }

        // if (blinds.estaPalhetaAberta()) {
        //     blinds.fecharPalheta();
        // }
        blinds.descerPalheta();
        blinds.fecharPalheta();
    }

}
