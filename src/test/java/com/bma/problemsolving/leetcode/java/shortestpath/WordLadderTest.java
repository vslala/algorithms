package com.bma.problemsolving.leetcode.java.shortestpath;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordLadderTest {

    private WordLadder.DFS solDFS = new WordLadder.DFS();
    private WordLadder.BFS solBfs = new WordLadder.BFS();

    @ParameterizedTest
    @CsvSource({
            "hit, cog, hot_dot_dog_lot_log_cog, 5",
            "hit, cog, hot_dot_dog_lot_log, 0",
            "hot, dog, hot_dog_dot, 3",
            "qa, sq, si_go_se_cm_so_ph_mt_db_mb_sb_kr_ln_tm_le_av_sm_ar_ci_ca_br_ti_ba_to_ra_fa_yo_ow_sn_ya_cr_po_fe_ho_ma_re_or_rn_au_ur_rh_sr_tc_lt_lo_as_fr_nb_yb_if_pb_ge_th_pm_rb_sh_co_ga_li_ha_hz_no_bi_di_hi_qa_pi_os_uh_wm_an_me_mo_na_la_st_er_sc_ne_mn_mi_am_ex_pt_io_be_fm_ta_tb_ni_mr_pa_he_lr_sq_ye, 5"
    })
    void shouldReturnTheShortestTransformationSequenceFromBeginWordToEndWord(String begin, String end, String dict, int expected) {
        List<String> wordList = new ArrayList<>(Arrays.asList(dict.split("_")));

        var result = solBfs.ladderLength(begin, end, wordList);

        assertEquals(expected, result);
    }
}