//
//  ActivityPackage.java
//  Adjust
//
//  Created by Christian Wellenbrock on 2013-06-25.
//  Copyright (c) 2013 adjust GmbH. All rights reserved.
//  See the file MIT-LICENSE for copying permission.
//

package com.adjust.sdk;

import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ActivityPackage implements Serializable {
    private static final long serialVersionUID = -35935556512024097L;
    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("path", String.class),
            new ObjectStreamField("clientSdk", String.class),
            new ObjectStreamField("parameters", (Class<Map<String,String>>)((Class)Map.class)),
            new ObjectStreamField("activityKind", ActivityKind.class),
            new ObjectStreamField("suffix", String.class)
    };

    // data
    private String path;
    private String clientSdk;
    private Map<String, String> parameters;

    // logs
    private ActivityKind activityKind;
    private String suffix;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getClientSdk() {
        return clientSdk;
    }

    public void setClientSdk(String clientSdk) {
        this.clientSdk = clientSdk;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public ActivityKind getActivityKind() {
        return activityKind;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public ActivityPackage(ActivityKind activityKind) {
        this.activityKind = activityKind;
    }

    public String toString() {
        return String.format("%s%s", activityKind.toString(), suffix);
    }

    public String getExtendedString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Path:      %s\n", path));
        builder.append(String.format("ClientSdk: %s\n", clientSdk));

        if (parameters != null) {
            builder.append("Parameters:");
            SortedMap<String,String> sortedParameters = new TreeMap<String,String>(parameters);
            for (Map.Entry<String,String> entry : sortedParameters.entrySet() ) {
                builder.append(String.format("\n\t%-16s %s", entry.getKey(),  entry.getValue()));
            }
        }
        return builder.toString();
    }

    protected String getFailureMessage() {
        return String.format("Failed to track %s%s", activityKind.toString(), suffix);
    }
}
