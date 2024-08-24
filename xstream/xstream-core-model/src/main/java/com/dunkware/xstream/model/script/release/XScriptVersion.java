package com.dunkware.xstream.model.script.release;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XScriptVersion {
    private int major;
    private int minor;
    private int revision;

    @Override
    public String toString() {
        return major + "." + minor + "." + revision;
    }

    public static XScriptVersion fromString(String version) {
        String[] parts = version.split("\\.");
        return new XScriptVersion(
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2])
        );
    }

    public static XScriptVersion incrementMajor(XScriptVersion version) {
        return new XScriptVersion(version.getMajor() + 1, 0, 0);
    }

    public static XScriptVersion incrementMinor(XScriptVersion version) {
        return new XScriptVersion(version.getMajor(), version.getMinor() + 1, 0);
    }

    public static XScriptVersion incrementRevision(XScriptVersion version) {
        return new XScriptVersion(version.getMajor(), version.getMinor(), version.getRevision() + 1);
    }
}
