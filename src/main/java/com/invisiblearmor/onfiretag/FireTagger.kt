package com.invisiblearmor.onfiretag

import org.bukkit.entity.Player

/**
 * プレイヤーにFire Tagを付けるクラス
 *
 * @property player Fire Tagを付ける対象のプレイヤー
 */
class FireTagger(private val player: Player) {

    /**
     * プレイヤーにFire Tagを付け、そのタグの持続時間も付与するメソッド
     * 付与時間のデフォルトは２０
     */
    fun putFireTag() {
        val defaultTaggedTime = 20
        FireTagData.fireTaggedPlayer[player] = defaultTaggedTime
    }

    /**
     * プレイヤーからFire Tagを外すメソッド
     */
    fun removeFireTag() {
        FireTagData.fireTaggedPlayer.remove(player)
    }

    /**
     * プレイヤーにFire Tagが付いているかどうか確認するメソッド
     *
     * @return 付いていればtrue、そうでなければfalseを返す
     */
    fun isPlayerTagged(): Boolean =
        FireTagData.fireTaggedPlayer.containsKey(player)

    /**
     * プレイヤーについているFire Tagの持続時間が切れたかどうか確認するメソッド
     *
     * @return 切れていればtrue、そうでなければfalseを返す
     */
    fun isTaggedTimeExpired(): Boolean =
        fetchFireTaggedTime() == 0

    /**
     * プレイヤーについているFire Tagの持続時間をリセットするメソッド
     */
    fun resetFireTaggedTime() {
        val defaultTaggedTime = 20
        changeFireTaggedTime(defaultTaggedTime)
    }

    /**
     * プレイヤーについているFire Tagの持続時間を変更するメソッド
     *
     * @param taggedTime 新しいFire Tagの持続時間
     */
    fun changeFireTaggedTime(taggedTime: Int) {
        FireTagData.fireTaggedPlayer[player] = taggedTime
    }

    /**
     * プレイヤーについているFire Tagの持続時間を取得するメソッド
     *
     * @return プレイヤーについているFire Tagの持続時間を返す、ついてなければ０を返す
     */
    fun fetchFireTaggedTime(): Int =
        FireTagData.fireTaggedPlayer[player] ?: 0
}
