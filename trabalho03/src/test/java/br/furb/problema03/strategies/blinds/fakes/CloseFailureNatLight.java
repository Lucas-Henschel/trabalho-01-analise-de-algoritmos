package br.furb.problema03.strategies.blinds.fakes;

import br.furb.analise.algoritmos.PersianaNatLight;

public class CloseFailureNatLight extends PersianaNatLight {
    private final Exception exception;
    public boolean lowerBladeCalled;
    public boolean closeBladeCalled;

    public CloseFailureNatLight(Exception exception) {
        this.exception = exception;
    }

    @Override
    public boolean estaPalhetaErguida() {
        return true;
    }

    @Override
    public boolean estaPalhetaAberta() {
        return true;
    }

    @Override
    public void descerPalheta() {
        lowerBladeCalled = true;
    }

    @Override
    public void fecharPalheta() throws Exception {
        closeBladeCalled = true;
        throw exception;
    }
}
