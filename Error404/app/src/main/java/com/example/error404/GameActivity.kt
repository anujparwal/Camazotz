package com.example.error404

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.error404.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGameBinding
    private var gameModel: GameModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listeners for the 3x3 grid
        val buttons = arrayOf(binding.btn0, binding.btn1, binding.btn2, binding.btn3,
            binding.btn4, binding.btn5, binding.btn6, binding.btn7, binding.btn8)
        buttons.forEach { it.setOnClickListener(this) }

        binding.startGameBtn.setOnClickListener { startGame() }

        GameData.gameModel.observe(this) {
            gameModel = it
            setUI()
        }
    }

    private fun setUI() {
        gameModel?.apply {

            val buttons = arrayOf(binding.btn0, binding.btn1, binding.btn2, binding.btn3,
                binding.btn4, binding.btn5, binding.btn6, binding.btn7, binding.btn8)

            filledPos.forEachIndexed { index, value ->
                buttons[index].text = value
            }

            binding.startGameBtn.visibility = if (gameStatus == GameStatus.JOINED || gameStatus == GameStatus.FINISHED) View.VISIBLE else View.INVISIBLE

            binding.gameStatusText.text = when (gameStatus) {
                GameStatus.CREATED -> "Game ID: $gameId"
                GameStatus.JOINED -> "Ready to Start!"
                GameStatus.INPROGRESS -> "Current Turn: $currentPlayer"
                GameStatus.FINISHED -> if (winner.isNotEmpty()) "$winner Wins!" else "It's a Draw!"
            }
        }
    }

    private fun startGame() {
        gameModel?.apply {
            updateGameData(GameModel(gameId = gameId, gameStatus = GameStatus.INPROGRESS))
        }
    }

    override fun onClick(v: View?) {
        gameModel?.apply {
            if (gameStatus != GameStatus.INPROGRESS) return

            val clickedPos = (v?.tag as String).toInt()
            if (filledPos[clickedPos].isEmpty()) {
                filledPos[clickedPos] = currentPlayer
                // FIXED: Toggle turn properly
                currentPlayer = if (currentPlayer == "X") "O" else "X"
                checkForWinner()
                updateGameData(this)
            }
        }
    }

    private fun checkForWinner() {
        val winningPos = arrayOf(
            intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
        )

        gameModel?.apply {
            for (i in winningPos) {
                if (filledPos[i[0]] == filledPos[i[1]] && filledPos[i[1]] == filledPos[i[2]] && filledPos[i[0]].isNotEmpty()) {
                    gameStatus = GameStatus.FINISHED
                    winner = filledPos[i[0]]
                }
            }
            if (filledPos.none { it.isEmpty() } && gameStatus != GameStatus.FINISHED) {
                gameStatus = GameStatus.FINISHED
            }
        }
    }

    private fun updateGameData(model: GameModel) {
        GameData.saveGameModel(model)
    }
}