package com.lan.game.activity.impl;

import com.lan.game.activity.IActivityTarget;

/**
 * @author lanbei
 * 领奖活动
 */
public class ActivityClaim extends AbstractActivity {
    /**
     * 活动开始
     *
     * @param target
     */
    @Override
    public void onStart(IActivityTarget target) {
        ActivityClaimRecord record = (ActivityClaimRecord) this.getRecord();
        if (record == null) {
            record = new ActivityClaimRecord();
            this.setRecord(record);
        }
        record.setClaim(false);
    }

    /**
     * 活动初始化
     *
     * @param target
     */
    @Override
    public void onInit(IActivityTarget target) {
        ActivityClaimRecord record = (ActivityClaimRecord) this.getRecord();
        if (record == null) {
            record = new ActivityClaimRecord();
            this.setRecord(record);
        }
        record.setClaim(false);
    }

    /**
     * 活动展示
     *
     * @param target
     */
    @Override
    public void onShow(IActivityTarget target) {

    }

    /**
     * 活动结束
     *
     * @param target
     */
    @Override
    public void onClose(IActivityTarget target) {
        ActivityClaimRecord record = (ActivityClaimRecord) this.getRecord();
        record.setClaim(false);
    }

    /**
     * 操作函数
     *
     * @param target
     * @param args   自定义参数
     */
    @Override
    public boolean onClaim(IActivityTarget target, Object args) {
        ActivityClaimRecord record = (ActivityClaimRecord) this.getRecord();
        boolean opSucc = record.isClaim();
        record.setClaim(true);
        return !opSucc;
    }
}
