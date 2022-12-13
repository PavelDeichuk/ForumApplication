package com.pavel.forumapplication.Service;

import com.pavel.forumapplication.Dto.BanDto;

import java.util.List;

public interface BanService {
    List<BanDto> GetAllBanUsers(int page, int size);

    BanDto AddUserToBan(Long ban_user, Long author_ban, Long rule_ban);

    BanDto DeleteBan(Long ban_user);
}
