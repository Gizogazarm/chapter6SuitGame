package id.rich.challengech5.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import id.rich.challengech5.R
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)

        val playername = intent.getStringExtra("player_name")
        val enemyname = intent.getStringExtra("enemy")

        val tvP1 = findViewById<TextView>(R.id.tv_p1)
        val ivBatuP1 = findViewById<ImageView>(R.id.iv_batup1)
        val ivKertasP1 = findViewById<ImageView>(R.id.iv_kertasp1)
        val ivGuntingP1 = findViewById<ImageView>(R.id.iv_guntingp1)

        val tvEnemy = findViewById<TextView>(R.id.tv_enemy)
        val ivBatuEnemy = findViewById<ImageView>(R.id.iv_batuenemy)
        val ivKertasEnemy = findViewById<ImageView>(R.id.iv_kertasenemy)
        val ivGuntingEnemy = findViewById<ImageView>(R.id.iv_guntingenemy)

        val close = findViewById<ImageView>(R.id.iv_close)
        val refresh = findViewById<ImageView>(R.id.iv_refresh)

        val player = Player()
        val enemy = Enemy()
        val game = GameBuilder(player, enemy)
        var result = ""
        var isEnemyChoose= false

        tvP1.setText(playername)
        tvEnemy.setText(enemyname)

        fun setEnableButtonP1(active: Boolean){
            ivBatuP1.isEnabled = active
            ivKertasP1.isEnabled = active
            ivGuntingP1.isEnabled = active
        }

        fun setEnableButtonEnemy(active: Boolean){
            ivBatuEnemy.isEnabled = active
            ivKertasEnemy.isEnabled = active
            ivGuntingEnemy.isEnabled = active
        }

        fun restart(){
            ivBatuP1.setBackgroundResource(0)
            ivKertasP1.setBackgroundResource(0)
            ivGuntingP1.setBackgroundResource(0)
            ivBatuEnemy.setBackgroundResource(0)
            ivKertasEnemy.setBackgroundResource(0)
            ivGuntingEnemy.setBackgroundResource(0)
            setEnableButtonP1(true)
            setEnableButtonEnemy(true)
            isEnemyChoose = false
        }

        fun openDialog(result: String){
            val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .create()
            val view = layoutInflater.inflate(R.layout.dialog_result,null)
            val buttonPlayAgain = view.findViewById<Button>(R.id.bt_dialogplayagain)
            val buttonBack = view.findViewById<Button>(R.id.bt_dialogback)
            val tvResult = view.findViewById<TextView>(R.id.tv_dialogresult)
            builder.setView(view)

            tvResult.setText(result)

            buttonPlayAgain.setOnClickListener {
                builder.dismiss()
                restart()
            }
            buttonBack.setOnClickListener {
                builder.dismiss()
                finish()
            }
            builder.setCanceledOnTouchOutside(false)
            builder.show()
        }

        fun showResult(){
            if(isEnemyChoose){
                if (player.getStatus() == "MENANG"){
                    result = "$playername\nMENANG!"
                }
                else if(enemy.getStatus() == "MENANG"){
                    result = "$enemyname\nMENANG!"
                }
                else{
                    result = "SERI!"
                }
                openDialog(result)
            }
        }

        fun start(){
            if (enemyname == "CPU"){
                game.computerChoose()

                Handler(Looper.getMainLooper()).postDelayed({
                    if(enemy.getPoint() == 2){
                        ivBatuEnemy.setBackgroundResource(R.drawable.background_btnclick)
                        Toast.makeText(this,"$enemyname Memilih Batu",Toast.LENGTH_SHORT).show()
                        isEnemyChoose = true
                        game.startGame()
                        showResult()
                    }
                    if(enemy.getPoint() == 1) {
                        ivGuntingEnemy.setBackgroundResource(R.drawable.background_btnclick)
                        Toast.makeText(this,"$enemyname Memilih Gunting",Toast.LENGTH_SHORT).show()
                        isEnemyChoose = true
                        game.startGame()
                        showResult()
                    }
                    if (enemy.getPoint() == 0){
                        ivKertasEnemy.setBackgroundResource(R.drawable.background_btnclick)
                        Toast.makeText(this,"$enemyname Memilih Kertas",Toast.LENGTH_SHORT).show()
                        isEnemyChoose = true
                        game.startGame()
                        showResult()
                    }
                },1000)
            }
            else{
                ivBatuEnemy.setOnClickListener {
                    ivBatuEnemy.setBackgroundResource(R.drawable.background_btnclick)
                    setEnableButtonEnemy(false)
                    enemy.setPoint(2)
                    Toast.makeText(this,"$enemyname Memilih Batu",Toast.LENGTH_SHORT).show()
                    game.startGame()
                    isEnemyChoose = true
                    showResult()
                }

                ivKertasEnemy.setOnClickListener {
                    ivKertasEnemy.setBackgroundResource(R.drawable.background_btnclick)
                    setEnableButtonEnemy(false)
                    enemy.setPoint(0)
                    Toast.makeText(this,"$enemyname Memilih Kertas",Toast.LENGTH_SHORT).show()
                    game.startGame()
                    isEnemyChoose = true
                    showResult()
                }

                ivGuntingEnemy.setOnClickListener {
                    ivGuntingEnemy.setBackgroundResource(R.drawable.background_btnclick)
                    setEnableButtonEnemy(false)
                    enemy.setPoint(1)
                    Toast.makeText(this,"$enemyname Memilih Gunting",Toast.LENGTH_SHORT).show()
                    game.startGame()
                    isEnemyChoose = true
                    showResult()
                }
            }
            //Log.d("getstatus", "1: ${player.getStatus()}, enemy : ${enemy.getStatus()} ")
            Log.d("isenemy", "isenemy : $isEnemyChoose")


        }

        ivBatuP1.setOnClickListener {
            ivBatuP1.setBackgroundResource(R.drawable.background_btnclick)
            setEnableButtonP1(false)
            player.setPoint(2)
            Toast.makeText(this,"$playername Memilih Batu",Toast.LENGTH_SHORT).show()
            start()
        }

        ivKertasP1.setOnClickListener {
            ivKertasP1.setBackgroundResource(R.drawable.background_btnclick)
            setEnableButtonP1(false)
            player.setPoint(0)
            Toast.makeText(this,"$playername Memilih Kertas",Toast.LENGTH_SHORT).show()
            start()
        }

        ivGuntingP1.setOnClickListener {
            ivGuntingP1.setBackgroundResource(R.drawable.background_btnclick)
            setEnableButtonP1(false)
            player.setPoint(1)
            Toast.makeText(this,"$playername Memilih Gunting",Toast.LENGTH_SHORT).show()
            start()
        }

        close.setOnClickListener {
            finish()
        }

        refresh.setOnClickListener {
            restart()
        }


    }
}

abstract class PlayerImplementation {
    abstract fun setPoint(choose: Int)
    abstract fun getPoint(): Int
    abstract fun setStatus(status: String)
    abstract fun getStatus(): String
}

class Player() {
    private var point = 0
    private var status = "KALAH"

    fun getPoint(): Int = point

    fun setPoint(choose: Int){
        this.point = choose
    }

    fun setStatus(status: String) {
        this.status = status
    }

    fun getStatus(): String = status
}

class Enemy: PlayerImplementation() {
    private var point = 0
    private var status = "KALAH"

    override fun getPoint(): Int = point

    override fun setPoint(choose: Int){
        this.point = choose
    }

    override fun setStatus(status: String) {
        this.status = status
    }

    override fun getStatus(): String = status
}

abstract class DeclareGame{
    abstract fun computerChoose()
    abstract fun startGame()
}

class GameBuilder(val player: Player, val enemy: Enemy) : DeclareGame(){

    override fun computerChoose(){
        val comChoose = Random.nextInt(0,3)
        enemy.setPoint(comChoose)
    }

    override fun startGame() {
        val player1Point = player.getPoint()
        val enemyPoint = enemy.getPoint()
        val calculate = player1Point - enemyPoint

        if (calculate == 1 || calculate == -2){
            player.setStatus("MENANG")
            enemy.setStatus("KALAH")
        }
        else if(calculate == 2 || calculate == -1){
            player.setStatus("KALAH")
            enemy.setStatus("MENANG")
        }
        else{
            player.setStatus("DRAW")
            enemy.setStatus("DRAW")
        }
    }
}