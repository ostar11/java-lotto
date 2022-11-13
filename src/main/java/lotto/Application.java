package lotto;

import java.util.List;
import lotto.view.LottoManager;

public class Application {
    public static void main(String[] args) {
        final LottoManager lottoManager = new LottoManager();
        final int lottoCount = lottoManager.inputLottoCount();
        System.out.println(lottoCount + "개를 구매했습니다.");

        final NumberGenerator numberGenerator = new NumberGenerator();
        Lotto[] lottos = new Lotto[lottoCount];
        for (int i = 0; i < lottos.length; i++) {
            lottos[i] = new Lotto(numberGenerator.createLottoNumbers());
        }
        lottoManager.printLottoNumbers(lottos);

        List<Integer> winningNumbers = numberGenerator.createWinningNumbers(lottoManager.inputWinningNumbers());
        WinningNumbers w = new WinningNumbers(winningNumbers);
        final int bonusNumber = numberGenerator.createBonusNumbers(winningNumbers, lottoManager.inputBonusNumber());

        LottoCalculator lottoCalculator = new LottoCalculator();
        for (Lotto lotto : lottos) {
            int correctCount = lottoCalculator.compare(lotto, w, bonusNumber);
            lottoCalculator.addRankCount(correctCount);
        }

        lottoManager.printWinningDetails(lottoCalculator);
        lottoManager.printRateOfReturn(lottoCalculator);
    }
}
