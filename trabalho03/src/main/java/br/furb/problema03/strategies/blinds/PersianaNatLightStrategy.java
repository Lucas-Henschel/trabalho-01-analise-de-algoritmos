package br.furb.problema03.strategies.blinds;

import br.furb.analise.algoritmos.PersianaNatLight;
import br.furb.problema03.exceptions.BlindOperationException;

public class PersianaNatLightStrategy implements BlindStrategy {
    private final PersianaNatLight blind;

    public PersianaNatLightStrategy(PersianaNatLight blind) {
        this.blind = blind;
    }

    @Override
    public void open() {
        try {
            if (!blind.estaPalhetaAberta()) {
                blind.abrirPalheta();
            }

            if (!blind.estaPalhetaErguida()) {
                blind.subirPalheta();
            }
        } catch (Exception exception) {
            throw new BlindOperationException("Não foi possível abrir as persianas NatLight", exception);
        }
    }

    @Override
    public void close() {
        try {
            if (blind.estaPalhetaErguida()) {
                blind.descerPalheta();
            }

            if (blind.estaPalhetaAberta()) {
                blind.fecharPalheta();
            }
        } catch (Exception exception) {
            throw new BlindOperationException("Não foi possível fechar as persianas NatLight", exception);
        }
    }
}
