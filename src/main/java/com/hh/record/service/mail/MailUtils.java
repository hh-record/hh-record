package com.hh.record.service.mail;

import java.util.Random;

public class MailUtils {

    public static final String START_CONTENT = "<div class=\"\"><div class=\"aHl\"></div><div id=\":ob\" tabindex=\"-1\"></div><div id=\":n4\" class=\"ii gt\" jslog=\"20277; u014N:xr6bB; 4:W251bGwsbnVsbCxbXV0.\"><div id=\":n5\" class=\"a3s aiL msg40231263648748703\"><u></u>\n" +
            "<div style=\"margin-bottom:0;padding-bottom:0;margin-top:0;margin-right:0;margin-left:0;padding-top:0;padding-right:0;padding-left:0;width:100%\"><table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse;margin:0 auto;min-width:100%\">\n" +
            "<tbody><tr><td style=\"display:none!important;font-size:1px;color:#ffffff;line-height:1px;max-height:0px;max-width:0px;opacity:0;overflow:hidden\"><div id=\"m_40231263648748703preheader\"></div></td></tr></tbody></table>\n" +
            "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0;border-collapse:collapse;margin:0 auto\"><tbody><tr><td><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;width:100%;min-width:100%\">\n" +
            "<tbody><tr><td valign=\"top\" style=\"vertical-align:top\"><table class=\"m_40231263648748703inner_table\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" border=\"0\" width=\"600\" style=\"border-collapse:collapse;margin:0 auto;width:600px\">\n" +
            "<tbody><tr><td style=\"text-align:center;font-size:1px;line-height:1px\"><div></div></td></tr></tbody></table></td></tr></tbody></table></td></tr><tr><td id=\"m_40231263648748703template-wrapper\" style=\"background-color:#ffffff\" bgcolor=\"#ffffff\">\n" +
            "<table id=\"m_40231263648748703body_section2_text\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" border=\"0\" width=\"100%\" style=\"border-collapse:collapse;margin:0 auto;min-width:100%\"><tbody><tr><td><table class=\"m_40231263648748703main\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" border=\"0\" width=\"640\" style=\"border-collapse:collapse;margin:0 auto;width:640px\">\n" +
            "<tbody><tr><td bgcolor=\"#ffffff\" style=\"background-color:#ffffff\"><table class=\"m_40231263648748703inner_table\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" border=\"0\" width=\"600\" style=\"border-collapse:collapse;margin:0 auto;width:600px\">\n" +
            "<tbody><tr><td height=\"10\" style=\"line-height:1px;font-size:1px\">&nbsp; </td></tr><tr><td style=\"font-size:16px;font-family:Helvetica,Arial,sans-serif;color:#333333;line-height:24px\" align=\"left\">\n" +
            "<div id=\"m_40231263648748703body_sec2_textsec\"><p style=\"font-family:Helvetica;color:#232f3e;font-size:14px\">hh-record 비밀번호를 변경해주세요.</p><p>본 메일은 비밀번호 변경을 위해 발송되는 메일입니다. 본인이 요청한 변경 사항이 아니라면 개인정보 보호를 위해 비밀번호를 재변경해주세요.</p><p>hh-record의 비밀번호를 변경하시려면 본인 인증을 위해 아래 6자리 코드를 입력해주세요.</p>\n" +
            "<div style=\"margin-top:0px;margin-bottom:0px\" id=\"m_40231263648748703Post-Event\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\"><tbody><tr><td style=\"padding:0px 0px 0px 0px\" valign=\"top\" align=\"center\">\n" +
            "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody><tr><td style=\"border-radius:3px;font-size:14px;font-family:Helvetica,Arial,sans-serif;line-height:24px\" bgcolor=\"#ff9900\" align=\"center\">\n" +
            "<a href=\"#\" style=\"font-size:14px;font-family:Helvetica,Arial,sans-serif;padding-top:5px;padding-bottom:5px;color:#ffffff;text-decoration:none;border-radius:3px;padding:5px 20px 5px 20px;border:2px solid #ff9900;display:inline-block\" target=\"_blank\">";

    public static final String END_CONTENT = "</a></td></tr></tbody></table></td></tr></tbody></table></div><hr style=\"height:1px;border:none;color:#e8e8e8;background-color:#e8e8e8;margin-top:20px\"></div></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody>";

    public static String getCertificationNum() {
        String num = "";
        for (int i = 0; i < 8; i++) {
            num += new Random().nextInt(9);
        }
        return num;
    }

}
