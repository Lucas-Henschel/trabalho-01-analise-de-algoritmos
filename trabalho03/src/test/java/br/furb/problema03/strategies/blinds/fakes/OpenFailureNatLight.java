package br.furb.problema03.strategies.blinds.fakes;

import br.furb.analise.algoritmos.PersianaNatLight;

public class OpenFailureNatLight extends PersianaNatLight {
    private final Exception exception;
    public boolean openBladeCalled;
    public boolean raiseBladeCalled;

    public OpenFailureNatLight(Exception exception) {
        this.exception = exception;
    }

    @Override
    public boolean estaPalhetaAberta() {
        return false;
    }

    @Override
    public boolean estaPalhetaErguida() {
        return false;
    }

    @Override
    public void abrirPalheta() {
        openBladeCalled = true;
    }

    @Override
    public void subirPalheta() throws Exception {
        raiseBladeCalled = true;
        throw exception;
    }
}
