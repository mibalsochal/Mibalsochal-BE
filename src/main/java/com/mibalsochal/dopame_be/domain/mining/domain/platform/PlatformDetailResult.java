package com.mibalsochal.dopame_be.domain.mining.domain.platform;

import java.util.List;

public record PlatformDetailResult(
        String title,
        String platformUrl,
        List<String> tags
) {
}
