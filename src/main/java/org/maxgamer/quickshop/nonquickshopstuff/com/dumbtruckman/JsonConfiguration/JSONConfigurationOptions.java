/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.maxgamer.quickshop.nonquickshopstuff.com.dumbtruckman.JsonConfiguration;

import org.bukkit.configuration.file.FileConfigurationOptions;
import org.jetbrains.annotations.NotNull;

public class JSONConfigurationOptions extends FileConfigurationOptions {

    private boolean enablePrettyPrint = true;

    protected JSONConfigurationOptions(@NotNull final JSONConfiguration configuration) {
        super(configuration);
    }

    @Override
    public @NotNull JSONConfiguration configuration() {
        return (JSONConfiguration) super.configuration();
    }

    @Override
    public @NotNull JSONConfigurationOptions copyDefaults(final boolean value) {
        super.copyDefaults(value);
        return this;
    }

    @Override
    public @NotNull JSONConfigurationOptions pathSeparator(final char value) {
        super.pathSeparator(value);
        return this;
    }

    @Override
    public @NotNull JSONConfigurationOptions header(final String value) {
        super.header(value);
        return this;
    }

    @Override
    public @NotNull JSONConfigurationOptions copyHeader(final boolean value) {
        super.copyHeader(value);
        return this;
    }

    /**
     * Sets whether or not to pretty print the json output of the configuration.
     *
     * @param enable Whether or not pretty printing should be enabled.
     * @return This object, for chaining.
     */
    public JSONConfigurationOptions prettyPrint(final boolean enable) {
        enablePrettyPrint = enable;
        return this;
    }

    /**
     * Gets whether or not to pretty print the json output of the configuration.
     *
     * @return Whether or not to pretty print the json.
     */
    public boolean prettyPrint() {
        return enablePrettyPrint;
    }

}
